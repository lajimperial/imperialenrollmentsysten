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
public class Subjects {
    int SUBid;
    String SUBcode;
    String SUBdesc;
    int SUBUNITS;
    String SUBsched;
    ImperialENROLLMENTSYSTEN a = new ImperialENROLLMENTSYSTEN();
    
public void SaveSubjectRecord(int SUBid, String SUBcode, String SUBdesc, int SUBUNITS, String SUBSched ){
    
    a.connectDB();
    String query = "INSERT INTO subjects VALUES(" + SUBid + ", '" + SUBcode + "', '" + SUBdesc + "', " + SUBUNITS + ", '" + SUBSched + "')";
    try {
        a.stl.executeUpdate(query);
        System.out.println("Added a new subject entry!");
        } catch (Exception ex) {
            System.out.println("SAVE FAILED");
    }
}
public void DeleteSubjectRecord(int SUBid){
    a.connectDB();
    String query  = "DELETE FROM subjects where SUBid = " + SUBid;
    try {
        a.stl.executeUpdate(query);
        System.out.println("Subject entry has been deleted!!!!");
    } catch (Exception ex) {
        System.out.println("DELETE FAILED");
        ex.printStackTrace();
    }
    
}
public void UpdateSubjectRecord(int SUBid, String SUBcode, String SUBdesc, int SUBUNITS, String SUBsched){
    a.connectDB();
    String query = "UPDATE subjects SET SUBcode=?, SUBdesc=?, SUBUNITS=?, SUBsched=? WHERE SUBid=?";
    try (PreparedStatement pstmt = a.con.prepareStatement(query)) {
        pstmt.setString(1, SUBcode);
        pstmt.setString(2, SUBdesc);
        pstmt.setInt(3, SUBUNITS);
        pstmt.setString(4, SUBsched);
        pstmt.setInt(5, SUBid);
        
        pstmt.executeUpdate();
        System.out.println("Subject entry has CHANGED!!!");
    } catch (SQLException ex) {
        System.out.println("UPDATE FAILED" + ex.getMessage());
        ex.printStackTrace();
    }
}
public ResultSet LoadSubjectRecords(){
    a.connectDB();
    String query = "Select * FROM subjects";
    try {
        return a.stl.executeQuery(query);
    }catch(Exception e) {
        System.out.println("Error fetching subject records because of " + e.getMessage());
        return null;
    }

}}