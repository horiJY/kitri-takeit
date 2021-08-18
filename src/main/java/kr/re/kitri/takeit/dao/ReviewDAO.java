package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.ReviewVO;

public class ReviewDAO {
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
	//mypage -> select
	public List<ReviewVO> selectUserReview(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT C.CLASSNAME, R.REVIEWDATE, R.RECOMMEND, R.CATEGRY"
				+" FROM CLASS C, (SELECT CLASSID, REVIEWDATE, RECOMMEND"
				+ "					FROM REVIEW"
				+ "					WHERE USERID = '" + id + "') R"
				+" WHERE C.CLASSID = R.CLASSID"
				+" ORDER BY C.CLASSID";
		Statement stmt = null;
		ResultSet rs = null;
		List<ReviewVO> rlist = new ArrayList<ReviewVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ReviewVO rvo = new ReviewVO();
				rvo.setClassName(rs.getString(1));
				rvo.setReviewDate(rs.getDate(2));
				rvo.setRecommend(rs.getInt(3));
				
				rlist.add(rvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return rlist;
		
	}
	
	//mypage -> insert
	public int insertReview(ReviewVO rvo) {
		Connection conn = DBConnect.getInstance();

		// sql
		String sql = "INSERT INTO REVIEW (CLASSID, USERID, CONTENT, RECOMMEND, BREGDATE)"
				+ " VALUES (?, ?, ?, ?, SYSDATE)";

		// prepared
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rvo.getClassId());
			pstmt.setString(2, rvo.getUserId());
			pstmt.setString(3, rvo.getContent());
			pstmt.setInt(4, rvo.getRecommend());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}

		return result;
	}
	
	//main, detail page -> select (recommend) 별점 count
	
	//mypage -> update
	public int updateReview(String content, int recommend, int classId, String userId ) {
		Connection conn = DBConnect.getInstance();
		String sql = "UPDATE REVIEW"
				+ " SET CONTENT =?,"
				+ " 	RECOMMEND =?"
				+ " WHERE USERID =?"
				+ " AND CLASSID =?";
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2,recommend);
			pstmt.setString(3, userId);
			pstmt.setInt(3,classId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	//mypage -> delete
	public int deleteReview(int classId, String userId) {
		Connection conn = DBConnect.getInstance();
		String sql = "DELETE FROM REVIEW"
				+ " WHERE USERID = ?"
				+ " AND CLASSID = ?";
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2,classId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
}
