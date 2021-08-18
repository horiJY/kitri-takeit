package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import db.DBConnect;
import vo.ClassVO;

public class ClassDAO {

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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// class-detail page -> SelectAll
	public List<ClassVO> selectDetail(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "SELECT * FROM CLASS WHERE CLASSID = '" + classId + "'";

		Statement stmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClassVO cvo = new ClassVO();

				cvo.setClassName(rs.getString("CLASSNAME"));
				cvo.setCreater(rs.getString("CREATER"));
				cvo.setRecommend(rs.getInt("RECOMMEND"));
				cvo.setFavorite(rs.getInt("FAVORITE"));
				cvo.setPrice(rs.getInt("PRICE"));
				cvo.setSale(rs.getInt("SALE"));
				cvo.setClassType(rs.getString("CLASSTYPE"));
				clist.add(cvo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return clist;
	}

	// pre-class page -> Select(className, create, classType, favorite)
	public List<ClassVO> selectPreClass() {
		Connection conn = DBConnect.getInstance();

		String sql = "SELECT CLASSNAME, CREATER, CLASSTYPE, FAVORITE FROM CLASS WHERE TYPE = 'P'";

		Statement stmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClassVO cvo = new ClassVO();

				cvo.setClassName(rs.getString(1));
				cvo.setCreater(rs.getString(2));
				cvo.setClassType(rs.getString(3));
				cvo.setFavorite(rs.getInt(4));
				clist.add(cvo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return clist;
	}
	
	
	public List<ClassVO> selectClassList(String category, String range, String type){
		Connection conn = DBConnect.getInstance();
		
		if(range == null || range.equals("null")) {
			range = "RECOMMEND";
		}
		
		String sql = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ "	  FROM CLASS "
				+ "	  WHERE TYPE = ? "
				+ "	  AND CATEGORY = ? "
				+ "	  ORDER BY "+range+" DESC";
		
		String sql2 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ "	   FROM CLASS "
				+ "	   WHERE TYPE = ? "
				+ "	   ORDER BY "+range+" DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		
		try {
			
			if(category==null || category.equals("null")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, type);
			}else{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(2, category);
				pstmt.setString(1, type);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ClassVO cvo = new ClassVO();
				
				cvo.setClassId(rs.getInt("CLASSID"));
				cvo.setClassName(rs.getString("CLASSNAME"));
				cvo.setCreater(rs.getString("CREATER"));
				cvo.setClassType(rs.getString("CLASSTYPE"));
				cvo.setPeriod(rs.getInt("PERIOD"));
				cvo.setRecommend(rs.getInt("RECOMMEND"));
				cvo.setDetail(rs.getString("DETAIL"));
				cvo.setPrice(rs.getInt("PRICE"));
				cvo.setSale(rs.getInt("SALE"));
				cvo.setCapacity(rs.getInt("CAPACITY"));
				cvo.setType(rs.getString("TYPE"));
				cvo.setFavorite(rs.getInt("FAVORITE"));
				cvo.setCategory(rs.getString("CATEGORY"));
				cvo.setOpenDate(rs.getDate("OPENDATE"));
				cvo.setCountdown(rs.getInt("COUNTDOWN"));
				clist.add(cvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);
		}
		return clist;
	}
	
	//select
	public List<ClassVO> selectClassPage(String category, String range, String type, int start, int end){
		if(range == null || range.equals("null")) {
			if(type.equals("O")) {
				range = "RECOMMEND";				
			}else if(type.equals("P")) {
				range = "FAVORITE";
			}
		}
		
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql1 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* "
				+ "      FROM (SELECT * "
				+ "            FROM CLASS"
				+ "			   WHERE CATEGORY = ? "
				+ "			   AND TYPE = ? "
				+ "			   ORDER BY "+range+" DESC ) A "
				+ "      ) "
				+ "WHERE RNUM BETWEEN ? AND ? ";
		
		String sql2 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* "
				+ "      FROM (SELECT * "
				+ "            FROM CLASS"
				+ "			   WHERE TYPE = ? "
				+ "			   ORDER BY "+range+" DESC ) A "
				+ "      ) "
				+ "WHERE RNUM BETWEEN ? AND ? ";
		
		//prepared
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		try {
			
			if(category==null || category.equals("null")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, type);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else{
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, category);
				pstmt.setString(2, type);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}
			
			//resultset
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ClassVO cvo = new ClassVO();
				cvo.setClassId(rs.getInt("CLASSID"));
				cvo.setClassName(rs.getString("CLASSNAME"));
				cvo.setCreater(rs.getString("CREATER"));
				cvo.setClassType(rs.getString("CLASSTYPE"));
				cvo.setRecommend(rs.getInt("RECOMMEND"));
				cvo.setFavorite(rs.getInt("FAVORITE"));
				cvo.setPrice(rs.getInt("PRICE"));
				cvo.setSale(rs.getInt("SALE"));
				cvo.setOpenDate(rs.getDate("OPENDATE"));
				cvo.setCountdown(rs.getInt("COUNTDOWN"));
				//result
				clist.add(cvo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);
		}
		return clist;
	}
	
	//pre-class page : favorite update 
	public int updateFavorite(int classId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "UPDATE CLASS SET FAVORITE = ("
				+ "        SELECT COUNT(*) FROM FAVORITE"
				+ "        WHERE CLASSID = ?)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}
	
	// insert
	public int registClass(ClassVO cvo, String classType) {
		Connection conn = DBConnect.getInstance();

		//대면
		String sql1 = "INSERT INTO CLASS(CLASSID, CLASSNAME, CREATER, CLASSTYPE, RECOMMEND, DETAIL, PRICE, CAPACITY, ADDRESS, TYPE, CATEGORY, OPENDATE)"
				+ "	   VALUES((SELECT COUNT(*)FROM CLASS)+1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE+7)";
		
		//비대면
		String sql2 = "INSERT INTO CLASS(CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, TYPE, CATEGORY, OPENDATE)"
				+ "	   VALUES((SELECT COUNT(*)FROM CLASS)+1, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE+7)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			if(classType.equals("ON")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, cvo.getClassName());
				pstmt.setString(2, cvo.getCreater());
				pstmt.setString(3, cvo.getClassType());
				pstmt.setInt(4, 0);
				pstmt.setString(5, cvo.getDetail());
				pstmt.setInt(6, cvo.getPrice());
				pstmt.setInt(7, cvo.getCapacity());
				pstmt.setString(8, cvo.getAddress());
				pstmt.setString(9, "P");
				pstmt.setString(10, cvo.getCategory());
			}else if(classType.equals("OFF")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, cvo.getClassName());
				pstmt.setString(2, cvo.getCreater());
				pstmt.setString(3, cvo.getClassType());
				pstmt.setInt(4, cvo.getPeriod());
				pstmt.setInt(5, 0);
				pstmt.setString(6, cvo.getDetail());
				pstmt.setInt(7, cvo.getPrice());
				pstmt.setString(8, "P");
				pstmt.setString(9, cvo.getCategory());
			}
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);
		}
		return result;
	}
	
	public int classUpdate(int favorite) {
		Connection conn = DBConnect.getInstance();
		//favorite >= 10
		String sql1 = "UPDATE CLASS SET TYPE = 'O' "
					+ "WHERE TYPE = 'P'"
					+ "AND FAVORITE >= 10"
					+ "AND OPENDATE <= SYSDATE";
		
		//favorite < 10
		String sql2 = "UPDATE CLASS SET TYPE = NULL "
					+ "WHERE TYPE = 'P'"
					+ "AND FAVORITE < 10"
					+ "AND OPENDATE <= SYSDATE";
		
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			if(favorite >= 10) {
				result = stmt.executeUpdate(sql1);
			}else{
				result = stmt.executeUpdate(sql2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, null, stmt, null);
		}
		return result;
	}
	
}
