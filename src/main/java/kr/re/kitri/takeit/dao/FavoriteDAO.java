package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnect;

public class FavoriteDAO {
	
	// closeAll
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// pre-class page : insert
	public int insertFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "INSERT INTO FAVORITE(USERID, CLASSID)"
				+ " VALUES(?, ?)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);
		}
		System.out.println(result);
		return result;
	}
	
	// pre-class page : delete
	public int deleteFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "DELETE FROM FAVORITE WHERE USERID = ? CLASSID = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}
	
	public int selectFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "SELECT COUNT(*) FROM FAVORITE WHERE USERID = ? AND CLASSID = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}
}
