/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imperialenrollmentsysten;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class Assign extends ImperialENROLLMENTSYSTEN {
    int subjid;
    int tid;

    public int getSubid() {
        return subjid;
    }

    public void setSubid(int subjid) {
        this.subjid = subjid;
    }
    
    public String assignSubj(int tid) {
        connectDB();
        
         try{
             String check = "SELECT COUNT(*) FROM Assign WHERE tid = ? AND subjid = ?";
             try (PreparedStatement checkPs = con.prepareStatement(check)){
                 checkPs.setInt(1,tid);
                 checkPs.setInt(2,getSubid());
                 ResultSet rs = checkPs.executeQuery();
                 if(rs.next() && rs.getInt(1) > 0) {
                     return "Teacher already added in this subject!";
                 }
                 
             }
             
             String sql = "INSERT INTO Assign (tid, subjid) VALUES (?, ?)";
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, tid);
             ps.setInt(2,getSubid());
             ps.executeUpdate();
             return "A subject has been assigned!";
         } catch (Exception x) {
             return "Error assigning because: " + x.getMessage();
         }
    }
    
    public String deleteSubj(int tid) {
        connectDB();
         try{
             String sql = "DELETE FROM Assign WHERE subjid = ? AND tid = ?";;
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, tid);
             ps.setInt(2,getSubid());
             ps.executeUpdate();
             return "Subject Deleted!";
         } catch (Exception x) {
             return "Error deleting because: " + x.getMessage();
         }
    }
         
    public ResultSet loadAssignmentbyTeacher(int tid){
        connectDB();
        try{
            String sql = """ 
                         SELECT a.SUBid, s.SUBcode, s.SUBdesc, s.SUBUNITS, s.SUBsched
                         FROM Assign a
                         JOIN subjects s ON a.subjid = s.SUBid
                         WHERE a.tid = ?
                         """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tid);
            return ps.executeQuery();
        } catch (Exception x) {
            System.out.println("Error loading enrollments: " + x.getMessage());
            return null;
        }
    }
    
    public boolean oneTOone(int tid, int subjid) {
    boolean taken = false;
    try {
        String check = "SELECT * FROM assign WHERE tid = ? OR subjid = ?";
        PreparedStatement ps = con.prepareStatement(check);
        ps.setInt(1, tid);
        ps.setInt(2, subjid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            taken = true;
        }

        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return taken;
}
    }



// CREATE TABLE Assign( aid INT PRIMARY KEY AUTO INCREMENT NOT NULL, tid INT NOT NULL, subjid INT NOT NULL, FOREIGN KEY (tid) REFERENCES teachers (teachID), FOREIGN KEY(subjid) REFERENCES subjects(SUBid));
// ALTER TABLE Teachers (change tid into primary key auto increment not null);
//subjects is already done.