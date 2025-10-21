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
public class Teachers {
    int teachID;
    String teachName;
    String Address;
    String Contact;
    String Email;
    String Dept;
    ImperialENROLLMENTSYSTEN a = new ImperialENROLLMENTSYSTEN();
    
    public void SaveTeacherRecord(int teachID, String teachName, String Address, String Contact, String Email, String Dept) {
    a.connectDB();
    String query;

    if (teachID == 0) {
        query = "INSERT INTO teachers (teachName, Address, Contact, Email, Dept) " +
                "VALUES('" + teachName + "', '" + Address + "', '" + Contact + "', '" + Email + "', '" + Dept + "')";
    } else {
        query = "INSERT INTO teachers (teachID, teachName, Address, Contact, Email, Dept) " +
                "VALUES(" + teachID + ", '" + teachName + "', '" + Address + "', '" + Contact + "', '" + Email + "', '" + Dept + "')";
    }

    try {
        a.stl.executeUpdate(query);
        System.out.println("Added a new teacher entry!");
    } catch (Exception ex) {
        System.out.println("SAVE FAILED");
        ex.printStackTrace();
    }
}


public void DeleteTeacherRecord(int teachID) {
    a.connectDB();
    String query = "DELETE FROM teachers WHERE teachID = " + teachID;
    try {
        a.stl.executeUpdate(query);
        System.out.println("Teacher entry has been deleted!");
    } catch (Exception ex) {
        System.out.println("DELETE FAILED: " + ex.getMessage());
        ex.printStackTrace();
    }
}

public void UpdateTeacherRecord(int teachID, String teachName, String Address, String Contact, String Email, String Dept) {
    a.connectDB();
    String query = "UPDATE teachers SET teachName=?, Address=?, Contact=?, Email=?, Dept=? WHERE teachID=?";
    try (PreparedStatement pstmt = a.con.prepareStatement(query)) {
        pstmt.setString(1, teachName);
        pstmt.setString(2, Address);
        pstmt.setString(3, Contact);
        pstmt.setString(4, Email);
        pstmt.setString(5, Dept);
        pstmt.setInt(6, teachID);

        pstmt.executeUpdate();
        System.out.println("Teacher entry has been updated!");
    } catch (SQLException ex) {
        System.out.println("UPDATE FAILED: " + ex.getMessage());
        ex.printStackTrace();
    }
}

public ResultSet LoadTeacherRecords() {
    a.connectDB();
    String query = "SELECT * FROM teachers";
    try {
        return a.stl.executeQuery(query);
    } catch (Exception e) {
        System.out.println("Error fetching teacher records: " + e.getMessage());
        return null;
    }
    
}

public ResultSet LoadAssignments(int teachID) throws SQLException{
    a.connectDB();
    String sql = """
                 SELECT a.subjid, s.SUBcode, s.SUBdesc, s.SUBUNITS, s.SUBsched
                 FROM Assign a
                 JOIN subjects s on a.subjid = s.SUBid
                 WHERE a.tid= ?
                 """;
    
    PreparedStatement pst = a.con.prepareStatement(sql);
    pst.setInt(1, teachID);
    return pst.executeQuery();
}

}




