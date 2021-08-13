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
		//클래스 qna
		String sql1 = "SELECT CLASSID, C.CLASSNAME, Q.QNATITLE, Q.QNADATE"
				+" FROM CLASS C, (SELECT CLASSID, QNATITLE, QNADATE, 'ART' AS CATEGRY FROM ART_QNA WHERE USERID = '" + id + "'"
				+" UNION ALL SELECT CLASSID, QNATITLE, QNADATE, 'COOKING' AS CATEGRY FROM COOKING_QNA WHERE USERID = '" + id + "'"
				+" UNION ALL SELECT CLASSID, QNATITLE, QNADATE, 'LANGUAGE' AS CATEGRY FROM LANGUAGE_QNA WHERE USERID = '" + id + "'"
				+" UNION ALL SELECT CLASSID, QNATITLE, QNADATE, 'PROGRAMMING' AS CATEGRY FROM PROGRAMMING_QNA WHERE USERID = '" + id + "'"
				+" UNION ALL SELECT CLASSID, QNATITLE, QNADATE, 'SPORT' AS CATEGRY FROM SPORT_QNA WHERE USERID = '" + id + "') Q"
				+" WHERE C.CLASSID = Q.CLASSID"
				+" ORDER BY Q.QNADATE DESC";
		//일반 qna
		String sql2 = "SELECT QNATITLE, QNADATE"
					+ " FROM QNA"
					+ " WHERE USERID ='" + id + "'"
					+ " ORDER BY QNADATE DESC;";
		Statement stmt = null;
		ResultSet rs = null;
		List<QnaVO> qlist = new ArrayList<QnaVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			
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
