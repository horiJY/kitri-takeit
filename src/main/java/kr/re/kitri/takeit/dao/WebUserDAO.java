package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import db.DBConnect;

public class WebUserDAO {
    public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
	try {
	    if (rs != null && !rs.isClosed()) {
		rs.close();
	    }
	    if (stmt != null && !stmt.isClosed()) {
		stmt.close();
	    }
	    if (pstmt != null && !pstmt.isClosed()) {
		pstmt.close();
	    }
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setWebUser(HashMap<String, String> UserInfo) {
	System.out.println("여기는setWebUser" + UserInfo);
	Connection conn = DBConnect.getInstance();
	// sql
	String sql = "INSERT INTO WEBUSER(userid,username,user_thumnail) VALUES(?,?,?)";
	// pstmt
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	boolean result = false;
	try {
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, UserInfo.get("id"));
	    pstmt.setString(2, UserInfo.get("nickname"));
	    pstmt.setString(3, UserInfo.get("thumnailURL"));

	    // resultset
	    rs = pstmt.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    closeAll(conn, pstmt, null, rs);
	}
    }

    public boolean getWebUser(String id) {
	Connection conn = DBConnect.getInstance();
	// sql
	String sql = "SELECT USERID FROM WEBUSER WHERE  USERID = ?";
	// pstmt
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	boolean result = false;
	try {
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, id);
	    // resultset
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
		// result
		result = true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    closeAll(conn, pstmt, null, rs);
	}

	return result;
    }

}
