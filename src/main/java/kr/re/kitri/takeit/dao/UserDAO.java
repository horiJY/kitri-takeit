package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnect;
import vo.UserVO;

public class UserDAO {
	//closeAll
	public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//login page -> Select
	
	//mypage -> select User Information
	public UserVO selectUserInfo(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT * FROM WEBUSER WHERE USERID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO uvo = new UserVO();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uvo.setUserId(rs.getString(1));
				uvo.setUserThumnail(rs.getString(2));
				uvo.setUserName(rs.getString(3));
				uvo.setUserType(rs.getString(4));
				uvo.setPhone(rs.getString(5));
				uvo.setPoint(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}
		return uvo;
		
	}
	
	//mypage -> myInfoUpdate
	public int updateUser(UserVO uvo) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
		
		String sql = "UPDATE WEBUSER SET USERNAME=?, USERPHONE=?"
				+ " WHERE USERID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uvo.getUserName());
			pstmt.setString(2, uvo.getPhone());
			pstmt.setString(3, uvo.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
	//deleteUser
	public int deleteUser(String id) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result =0;
		
		String sql = "DELETE FROM WEBUSER"
				+ " WHERE USERID =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	//잔여 포인트 조회
	public int selectPoint(String userid) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		String sql = " SELECT POINT FROM WEBUSER WHERE USERID = '"+userid+"' ";
		Statement stmt = null;
		ResultSet rs = null;
		int point = -1;
		try {
			//쿼리 설정
			stmt = conn.createStatement();
			//쿼리 실행
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				point = rs.getInt("POINT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,null,stmt,null);
		}
		return point;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
