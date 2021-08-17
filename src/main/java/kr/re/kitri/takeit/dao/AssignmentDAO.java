package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DBConnect;
import vo.AssignmentVO;

public class AssignmentDAO {
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

	//강의 신청 페이지 -> Insert(등록)
	public int insertAssignment(AssignmentVO avo){
		Connection conn = DBConnect.getInstance();
		String sql = " INSERT INTO ASSIGNMENT ( CLASSID, USERID, STARTDATE, ENDDATE ) "
				+ " VALUES ( ?, ?, ?, ? ) ";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			//set prepared statement
			pstmt.setInt(1,avo.getClassId());
			pstmt.setString(2, avo.getUserId());
			SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			pstmt.setString(3,dateform.format(avo.getStartDate()));
			pstmt.setString(4, dateform.format(avo.getEndDate()));
			
			//get result
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}
	
	
	//마이페이지 -> Delete(수강 취소)
	
}
