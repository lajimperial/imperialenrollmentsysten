/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imperialenrollmentsysten;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author lajimperial
 */
public class Students extends ImperialENROLLMENTSYSTEN {
    int studentID;
    String Name;
    String Address; 
    String Contact;
    String Email;
    String Gender;
    String yearLvl;
    ImperialENROLLMENTSYSTEN a = new ImperialENROLLMENTSYSTEN();
    
public void SaveRecord(int studentID, String Name, String Address, String Contact, String Email, String Gender, String yearLvl ){
    a.connectDB();
    String query;
    if (studentID == 0){
    query = "INSERT INTO students(Name, Address, Contact, Email, Gender, yearLvl) VALUES('" + Name + "', '" + Address + "', '" + Contact + "', '" + Email + "', '" + Gender + "', '" + yearLvl + "')";
    } else {
        query = "INSERT INTO students (studentID, Name, Address, Contact, Email, Gender, yearLvl) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }
    
    try {
        a.stl.executeUpdate(query);
        System.out.println("Added a new entry!");
        } catch (Exception ex) {
            System.out.println("SAVE FAILED: " + ex.getMessage());
            ex.printStackTrace();
    }
}
public void DeleteRecord(int studentID){
    a.connectDB();
    String query  = "DELETE FROM students where studentID = " + studentID;
    try {
        a.stl.executeUpdate(query);
        System.out.println("Entry has been deleted!!!!");
    } catch (Exception ex) {
        System.out.println("DELETE FAILED");
        ex.printStackTrace();
    }
    
}
public void UpdateRecord(int studentID, String Name, String Address, String Contact, String Email, String Gender, String yearLvl ){
    a.connectDB();
    String query = "UPDATE students SET Name=?, Address=?, Contact=?, Email=?, Gender=?, yearLvl=? WHERE studentID=?";
    try (PreparedStatement pstmt = a.con.prepareStatement(query)) {
        pstmt.setString(1, Name);
        pstmt.setString(2, Address);
        pstmt.setString(3, Contact);
        pstmt.setString(4, Email);
        pstmt.setString(5, Gender);
        pstmt.setString(6, yearLvl);
        pstmt.setInt(7, studentID);
        
        pstmt.executeUpdate();
        System.out.println("Entry has CHANGED!!!");
    } catch (SQLException ex) {
        System.out.println("UPDATE FAILED" + ex.getMessage());
        ex.printStackTrace();
    }
}
public ResultSet LoadRecord(){
    a.connectDB();
    String query = "Select * FROM students";
    try {
        return a.stl.executeQuery(query);
    }catch(Exception e) {
        System.out.println("Error fetching records beause of " + e.getMessage());
        return null;
    }

}
public ResultSet LoadEnrollments(int studid) throws SQLException {
    a.connectDB(); // Connect using your existing DB connection method
String sql = """
    SELECT e.subjid, s.SUBcode, s.SUBdesc, s.SUBUNITS, s.SUBsched
    FROM Enroll e
    JOIN subjects s ON e.subjid = s.SUBid
    WHERE e.studid = ?
""";

    PreparedStatement pst = a.con.prepareStatement(sql);
    pst.setInt(1, studid);
    return pst.executeQuery();
}

}

