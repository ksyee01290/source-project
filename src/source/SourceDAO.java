package source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SourceDAO {
    private Connection conn;
    private ResultSet rs;

    public SourceDAO(){
        try{
            String dbURL = "jdbc:mysql://localhost:3306/source?characterEncoding=UTF-8&serverTimezone=UTC";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            System.out.println(conn);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getDate() {
    	String SQL = "SELECT NOW()";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			return rs.getString(1);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
    
    public int getNext() {
    	String SQL = "SELECT sourceID FROM SOURCE ORDER BY sourceID DESC";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			return rs.getInt(1) +1;
    		}
    		return 1;
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    public int write(String sourceTitle, String userID, String sourceContent) {
    	String SQL = "INSERT INTO SOURCE VALUES(?, ?, ?, ?, ?, ?)";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, getNext());
    		pstmt.setString(2, sourceTitle);
    		pstmt.setString(3, userID);
    		pstmt.setString(4, getDate());
    		pstmt.setString(5, sourceContent);
    		pstmt.setInt(6, 1);
    		return pstmt.executeUpdate();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    	
    }
    public ArrayList<Source> getList(int pageNumber){
    	String SQL = "SELECT * FROM SOURCE WHERE sourceID <? AND sourceAvailable = 1 ORDER BY sourceID DESC LIMIT 10";
    	ArrayList<Source> list = new ArrayList<Source>();
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, getNext() - (pageNumber -1)*10);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			Source source = new Source();
    			source.setSourceID(rs.getInt(1));
    			source.setSourceTitle(rs.getString(2));
    			source.setUserID(rs.getString(3));
    			source.setSourceDate(rs.getString(4));
    			source.setSourceContent(rs.getString(5));
    			source.setSourceAvailable(rs.getInt(6));
    			list.add(source);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public boolean nextPage(int pageNumber) {
    	String SQL = "SELECT * FROM SOURCE WHERE sourceID < ? AND sourceAvailable = 1";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, getNext() - (pageNumber -1)*10);
    		rs = pstmt.executeQuery();
    		if (rs.next()) {
    			return true;
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    public Source getSource(int sourceID) {
    	String SQL = "SELECT * FROM SOURCE WHERE sourceID = ?";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, sourceID);
    		rs = pstmt.executeQuery();
    		if (rs.next()) {
    			Source source = new Source();
    			source.setSourceID(rs.getInt(1));
    			source.setSourceTitle(rs.getString(2));
    			source.setUserID(rs.getString(3));
    			source.setSourceDate(rs.getString(4));
    			source.setSourceContent(rs.getString(5));
    			source.setSourceAvailable(rs.getInt(6));
    			return source;
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}