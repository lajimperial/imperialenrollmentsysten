package com.mycompany.imperialenrollmentsysten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.sql.DriverManager;

public class ImperialENROLLMENTSYSTEN {

    static Connection con;
    static Statement stl;
    static ResultSet rs;
            
    static String db;
    static String uname;
    static String pswd;
    
    public static void main(String[] args) {
        connectDB();
        StudentsForm a = new StudentsForm();
        a.setVisible(true);
        System.out.println( "jdbc:mysql://localhost:3306/" + db + "?zeroDateTimeBehavior=convertToNull" + uname + pswd);
        
    }
    
    public static void connectDB() {
        db = "1stsemsy25_26";
        uname = "root";
        pswd = "root";
        try {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + db + "?zeroDateTimeBehavior=convertToNull", 
                uname, pswd
            );
            stl = con.createStatement();  
            System.out.println("Connected");
        } catch (Exception ex) {
            System.out.println("Failed to Connect: " + ex);
        }        
    }
}



