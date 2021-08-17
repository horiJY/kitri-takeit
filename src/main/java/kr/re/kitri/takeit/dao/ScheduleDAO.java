package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public int insertSchedule(List<ScheduleVO> slist) {
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
			//date -> string 변환
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
	public int selectScheduleOverlap(String userId, Date stime, Date etime) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		String sql = " SELECT COUNT(*) FROM SCHEDULE WHERE "
				+ " CLASSID IN ( SELECT CLASSID FROM ASSIGNMENT WHERE USERID = ? ) "
				+ " OR CLASSID IN ( SELECT CLASSID FROM CLASS WHERE CREATER = ? ) "
				+ " AND TO_CHAR( ENDTIME, 'YYYY-MM-DD HH24:MI ) > ? "
				+ " AND TO_CHAR( STARTTIME, 'YYYY-MM-DD HH24:MI ) < ? ";
		PreparedStatement pstmt= null;
		int result = 0;
		try {
			//쿼리 설정
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			pstmt.setString(3, dateform.format(stime));
			pstmt.setString(4, dateform.format(etime));
			//쿼리 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,null,null);
		}
		return result;
	}
	
	//클래스의 첫 일정 조회
	public Date selectMinSchedule(int classId) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		String sql = " SELECT MIN(STARTTIME) FROM SCHEDULE WHERE "
				+ " CLASSID ='"+classId+"' ";
		Statement stmt = null;
		ResultSet rs = null;
		Date startdate = null;
		try {
			//쿼리 설정
			stmt= conn.createStatement();
			//쿼리 실행
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				startdate = rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,null,stmt,null);
		}
		return startdate;
	}
	public Date selectMaxSchedule(int classId) {
		//db 접속
		Connection conn = DBConnect.getInstance();
		//sql 작성
		String sql = " SELECT MAX(STARTTIME) FROM SCHEDULE WHERE "
				+ " CLASSID ='"+classId+"' ";
		Statement stmt = null;
		ResultSet rs = null;
		Date enddate = null;
		try {
			//쿼리 설정
			stmt= conn.createStatement();
			//쿼리 실행
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				enddate = rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,null,stmt,null);
		}
		return enddate;
	}
}
