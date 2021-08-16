package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import db.DBConnect;
import vo.ScheduleVO;

public class ScheduleDAO {
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

	//스케쥴 생성
	public int insertSchedule(ArrayList<ScheduleVO> slist) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO SCHEDULE ( CLASSID, DAY, STARTTIME, ENDTIME ) VALUES ");
		for(int i=0;i<slist.size();i++) {
			ScheduleVO svo = slist.get(i);
			sb.append("( ");
			sb.append(svo.getClassId());
			sb.append(", ");
			sb.append(svo.getStartTime());
			sb.append(", ");
			sb.append(svo.getEndTime());
			sb.append(") ");
			if(i+1<slist.size()) sb.append(", ");
		}
		String sql = sb.toString();
		Statement stmt = null;
		int result = 0;
		try {
			//쿼리 설정
			stmt = conn.createStatement();
			//쿼리 실행
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,null,stmt,null);
		}
		
		return result;
	}
	
	//해당 클래스 id에서 겹치는 일정 조회
	public int selectScheduleOverlap(String userId, String stime, String etime) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		String sql = " SELECT COUNT(*) FROM SCHEDULE WHERE "
				+ " CLASSID IN ( SELECT CLASSID FROM ASSIGNMENT WHERE USERID = ? ) "
				+ " OR CLASSID IN ( SELECT CLASSID FROM CLASS WHERE CREATER = ? ) "
				+ " AND TO_CHAR( ENDTIME, 'YYYY-MM-DD HH24:MI ) > ? "
				+ " AND TO_CHAR( STARTTIME, 'YYYY-MM-DD HH24:MI ) < ? ";
		PreparedStatement psmt = null;
		int result = 0;
		try {
			//쿼리 설정
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, userId);
			psmt.setString(3, stime);
			psmt.setString(4, etime);
			//쿼리 실행
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,psmt,null,null);
		}
		return result;
	}
}
