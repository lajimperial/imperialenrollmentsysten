package com.mycompany.imperialenrollmentsysten;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Enroll extends ImperialENROLLMENTSYSTEN {
    int studid;
    int subjid;

    public int getSubid() { return subjid; }
    public void setSubid(int subjid) { this.subjid = subjid; }
    public void setStudid(int studid) { this.studid = studid; }
    public int getStudid() { return studid; }

    public String enrollSubj(int studid) {
        connectDB();
        try {
            // Check duplicate
            String check = "SELECT COUNT(*) FROM Enroll WHERE studid = ? AND subjid = ?";
            try (PreparedStatement checkPs = con.prepareStatement(check)) {
                checkPs.setInt(1, studid);
                checkPs.setInt(2, getSubid());
                ResultSet rs = checkPs.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return "Student already enrolled in this subject!";
                }
            }

            // Insert
            String sql = "INSERT INTO Enroll(studid, subjid) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, studid);
                ps.setInt(2, getSubid());
                ps.executeUpdate();
                return "Student successfully enrolled!";
            }
        } catch (Exception x) {
            return "Error assigning because: " + x.getMessage();
        }
    }

    public String dropSubj(int studid) {
        connectDB();
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Enroll WHERE studid = ? AND subjid = ?")) {
            ps.setInt(1, studid);
            ps.setInt(2, getSubid());
            ps.executeUpdate();
            return "Subject successfully dropped!";
        } catch (Exception x) {
            return "Error dropping because: " + x.getMessage();
        }
    }

    public ResultSet loadEnrollmentsByStudent(int studid) {
        connectDB();
        try {
            String sql = """
                SELECT e.SUBid, s.SUBcode, s.SUBdesc, s.SUBUNITS, s.SUBsched
                FROM Enroll e
                JOIN subjects s ON e.subjid = s.SUBid
                WHERE e.studid = ?
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studid);
            return ps.executeQuery();
        } catch (Exception x) {
            System.out.println("Error loading enrollments: " + x.getMessage());
            return null;
        }
    }
}
