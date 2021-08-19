package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.ClassQnaVO;
import vo.QnaVO;

public class ClassQnaDAO {
	// closeAll
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

	// mypage -> Insert(qna등록)

	// mypage -> SelectAll(qna 및 답변 확인)
	public List<ClassQnaVO> selectMyQna(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, QNATITLE, QNADATE, QUESTION, ANSWER" + " FROM CLASS C, CLASS_QNA Q"
				+ " WHERE C.CLASSID = Q.CLASSID" + " AND Q.USERID='" + id + "'" + " ORDER BY Q.QNADATE DESC";
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassQnaVO> cqlist = new ArrayList<ClassQnaVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClassQnaVO cqvo = new ClassQnaVO();
				cqvo.setClassName(rs.getString(1));
				cqvo.setQnaTitle(rs.getString(2));
				cqvo.setQnaDate(rs.getDate(3));
				cqvo.setQuestion(rs.getString(4));
				cqvo.setAnswer(rs.getString(5));

				cqlist.add(cqvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return cqlist;
	}

	// mypage -> selectMyClassQna 내가 개설한 클래스 qna 목록
	public List<ClassQnaVO> selectMyClassQna(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT C.CLASSNAME, Q.QNATITLE, Q.QNADATE, Q.USERID, C.CLASSID, Q.QUESTION, Q.ANSWER"
				+ " FROM CLASS_QNA Q, CLASS C" + " WHERE C.CLASSID = Q.CLASSID" + " AND C.CREATER ='" + id + "'"
				+ " ORDER BY Q.QNADATE DESC";
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassQnaVO> cqlist = new ArrayList<ClassQnaVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClassQnaVO cqvo = new ClassQnaVO();
				cqvo.setClassName(rs.getString(1));
				cqvo.setQnaTitle(rs.getString(2));
				cqvo.setQnaDate(rs.getDate(3));
				cqvo.setUserId(rs.getString(4));
				cqvo.setClassId(rs.getInt(5));
				cqvo.setQuestion(rs.getString(6));
				cqvo.setAnswer(rs.getString(7));

				cqlist.add(cqvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return cqlist;
	}

	// mypage -> question Update
	public int updateUserQna(String qnaTitle, String userId, String question, String className) {
		Connection conn = DBConnect.getInstance();
		String sql = "UPDATE CLASS_QNA SET QUESTION='" + question + "'" 
				+ " WHERE QNATITLE='" + qnaTitle + "'"
				+ " AND USERID='" + userId + "'"
				+ " AND CLASSID = (SELECT CLASSID FROM CLASS WHERE CLASSNAME='" + className + "')";
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}

		return result;
	}
	// mypage -> answer Update
	public int updateClassQna(String qnaTitle, String userId, String answer, String className) {
		Connection conn = DBConnect.getInstance();
		String sql = "UPDATE CLASS_QNA SET ANSWER='" + answer + "'" 
				+ " WHERE QNATITLE='" + qnaTitle + "'"
				+ " AND USERID='" + userId + "'"
				+ " AND CLASSID = (SELECT CLASSID FROM CLASS WHERE CLASSNAME='" + className + "')";
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}

		return result;
	}
	
	// mypage -> Delete
	public int deletClasseQna(String userId, String qnaTitle, String className) {
		Connection conn = DBConnect.getInstance();
		String sql = "DELETE FROM CLASS_QNA" 
				+ " WHERE USERID = '" + userId + "'" 
				+ " AND QNATITLE = '" + qnaTitle + "'" 
				+ " AND CLASSID = (SELECT CLASSID FROM CLASS WHERE CLASSNAME='" + className + "')";
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}

		return result;
	}
}
