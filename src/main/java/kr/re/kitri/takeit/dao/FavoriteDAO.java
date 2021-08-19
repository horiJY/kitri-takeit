package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.ClassVO;

public class FavoriteDAO {
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

	public int selectFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "SELECT COUNT(*) FROM FAVORITE WHERE USERID = ? AND CLASSID = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}

	// mypage -> select favorite class
	public List<ClassVO> selectFavoriteClass(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSID, CLASSNAME, CREATER, FAVORITE, OPENDATE FROM CLASS "
				+ "WHERE CLASSID = (SELECT CLASSID FROM FAVORITE WHERE USERID = '" + id + "')" + "AND TYPE='P'";
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClassVO cvo = new ClassVO();
				cvo.setClassId(rs.getInt(1));
				cvo.setClassName(rs.getString(2));
				cvo.setCreater(rs.getString(3));
				cvo.setFavorite(rs.getInt(4));
				cvo.setOpenDate(rs.getDate(5));

				clist.add(cvo);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return clist;
	}

	// pre-class page : insert
	public int insertFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "INSERT INTO FAVORITE(USERID, CLASSID)" + " VALUES(?, ?)";

		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		System.out.println("DAO" + result);
		return result;
	}

	// pre-class page : delete
	public int deleteFavorite(String userId, int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "DELETE FROM FAVORITE WHERE USERID = ? CLASSID = ?";

		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, classId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}

	// pre-class page : favorite update
	public int updateFavorite(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "UPDATE CLASS SET FAVORITE = (" + "        SELECT COUNT(*) FROM FAVORITE"
				+ "        WHERE CLASSID = ?) WHERE CLASSID = ?";

		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classId);
			pstmt.setInt(2, classId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		System.out.println("CDAO:" + result);
		return result;
	}

}
