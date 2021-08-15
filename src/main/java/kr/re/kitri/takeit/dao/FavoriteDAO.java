package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.FavoriteVO;


public class FavoriteDAO {
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
	//mypage - select
	public List<FavoriteVO> selectFavorite(String id){
		Connection conn = DBConnect.getInstance();
		String sql ="SELECT * FROM MEMO WHERE ID ='"+id+"' ORDER BY CLASSID DESC";
		Statement stmt = null;
		ResultSet rs = null;
		List<FavoriteVO> flist = new ArrayList<FavoriteVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				FavoriteVO fvo = new FavoriteVO();
				fvo.setUserId(rs.getString(1));
				fvo.setClassId(rs.getInt(2));
				
				flist.add(fvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return flist;
	}
	//pre-class page - insert

	//pre-class page - delete
}
