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
	
	//mypage -> admin
	//select
	public List<QnaVO> selectAllQna(){
		Connection conn = DBConnect.getInstance();
		String sql =" SELECT USERID, QNADATE, QUESTION, ANSWER, QNATITLE"
				+ " FROM QNA"
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
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return qlist;
	}
	//update
	public int updateQna(String qnaTitle,String userId, String answer){
		Connection conn = DBConnect.getInstance();
		String sql ="UPDATE QNA SET ANSWER='" + answer + "'"
				+ " WHERE QNATITLE='" + qnaTitle + "'"
				+ " AND USERID='" + userId + "'";
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
	
	//mypage -> 일반 qna
	public List<QnaVO> selectMyQna(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT USERID, QNADATE, QUESTION, ANSWER, QNATITLE"
					+ " FROM QNA"
					+ " WHERE USERID ='" + id + "'"
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
	public int deletQna(String userId) {
		Connection conn = DBConnect.getInstance();
		String sql = "DELETE FROM CLASSQNA"
				+ " WHERE USERID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	public int qna(QnaVO qvo ) {
        Connection conn = DBConnect.getInstance();
        
        String sql = "INSERT INTO QNA (USERID, QNADATE, QUESTION, QNATITLE)"
                + "VALUES (?,sysdate,?,?)";
        
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, qvo.getUserid());
            pstmt.setString(2, qvo.getQuestion());
            pstmt.setString(3, qvo.getQnatitle());
            
            
            result = pstmt.executeUpdate();
        
        
        
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeAll(conn,pstmt,null,null);
        }
        return result;
    }
}
