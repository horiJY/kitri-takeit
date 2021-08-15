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
				+" FROM CLASS C, (SELECT CLASSID, REVIEWDATE, RECOMMEND, CATEGRY "
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
				rvo.setCategory(rs.getString(4));;
				
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
	
	
	//main, detail page -> select (recommend) 별점 count
	
	
	//mypage -> delete
	
	
	//mypage -> update
}
