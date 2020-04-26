package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDAO(){
        try{
            String dbURL = "jdbc:mysql://localhost:3306/source_db";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("com.mysql.jdbc.Drvier");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public int login(String userID, String userPassword){
        String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
        
    }

}