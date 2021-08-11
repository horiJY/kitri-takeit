package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.QnaVO;

public class QnaDAO {
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
	
	//help page -> Insert(qna등록)
	
	//mypage -> SelectAll(qna 및 답변 확인)
	public List<QnaVO> selectMyQnA(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT *"
				+ " FROM QNA, SPORT_QNA, COOKING_QNA, PROGRAMMING_QNA, ART_QNA, LANGUAGE_QNA"
				+ " WHERE USERID = '" +id+ "'"
				+ " ORDER BY QNADATE DESC";
		Statement stmt = null;
		ResultSet rs = null;
		List<QnaVO> qlist = new ArrayList<QnaVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				QnaVO qvo = new QnaVO();
				qvo.setUserId(rs.getString(1));
				qvo.setQnaDate(rs.getDate(2));
				qvo.setQuestion(rs.getString(3));
				qvo.setAnswer(rs.getString(4));
				qvo.setQnaTitle(rs.getString(5));
				
				qlist.add(qvo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return qlist;
		
	}
	
	//mypage -> Delete
}
