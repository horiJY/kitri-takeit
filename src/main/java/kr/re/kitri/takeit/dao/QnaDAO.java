package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnect;
import vo.QnaVO;





public class QnaDAO {
	
	public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		try {
			if(rs != null&& !rs.isClosed()) {
				rs.close();
			}if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
			
		}catch(Exception e) {
			
		}
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
