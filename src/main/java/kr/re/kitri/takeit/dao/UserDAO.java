package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnect;
import vo.ScheduleVO;

public class UserDAO {
	//closeAll
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//login page -> Select
	
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
