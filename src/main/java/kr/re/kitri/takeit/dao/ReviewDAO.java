package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.ReviewVO;

public class ReviewDAO {
	// closeAll
	public void closeAll(Connection conn, PreparedStatement pstmt,
			Statement stmt, ResultSet rs) {
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
			// e.printStackTrace();
		}
	}

	// mypage -> insert

	// main, detail page -> select

	// detail page 해당 class 후기 개수 및 (recommend) 별점 count -> select
	public ReviewVO getRecommandCountAndScore(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "select count(*), AVG(recommend) " + "from class_review "
				+ "where classid= " + classId + " group by classid";

		Statement stmt = null;
		ResultSet rs = null;
		ReviewVO rvo = new ReviewVO();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				rvo.setTotalRecommendNum(rs.getInt(1));
				rvo.setAvgScore(rs.getDouble(2));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return rvo;
	}

	// detail page 해당 class 최근 후기 2개 -> select
	public List<ReviewVO> getRecentRecommands(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "select * "
				+ "from (select wu.username, cr.content, cr.reviewdate "
				+ "        from class_review cr, webuser wu "
				+ "        where cr.userid=wu.userid and classid = " + classId
				+ "        )" + "where rownum <= 2";

		Statement stmt = null;
		ResultSet rs = null;
		List<ReviewVO> rlist = new ArrayList<ReviewVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReviewVO rvo = new ReviewVO();

				rvo.setUserId(rs.getString(1));
				rvo.setContent(rs.getString(2));
				rvo.setReviewDate(rs.getDate(3));
				rlist.add(rvo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return rlist;
	}

	// mypage -> delete

	// mypage -> update
}
