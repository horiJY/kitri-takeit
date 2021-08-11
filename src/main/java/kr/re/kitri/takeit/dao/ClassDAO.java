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

				cvo.setClassName(rs.getString(1));
				cvo.setCreater(rs.getString(2));
				cvo.setRecommend(rs.getInt(3));
				cvo.setPrice(rs.getInt(4));
				cvo.setSale(rs.getInt(5));
				cvo.setClassType(rs.getString(6));
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

	//select
	public List<ClassVO> selectClassPage(String category, String range, int start, int end){
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql1 : 카테고리x
		String sql1 = "SELECT * "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* "
				+ "      FROM (SELECT * "
				+ "            FROM CLASS "
				+ "			   WHERE TYPE = ?"
				+ "            ORDER BY ? DESC) A "
				+ "      ) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		//sql2 : 카테고리o
		String sql2 = "SELECT * "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* "
				+ "      FROM (SELECT * "
				+ "            FROM CLASS "
				+ "			   WHERE CATEGORY = ?"
				+ "			   AND TYPE = ?"
				+ "            ORDER BY ? DESC) A "
				+ "      ) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		
		//prepared
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		
		try {
			if(category == null) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, "O");
				pstmt.setString(2, range);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, category);
				pstmt.setString(2, "O");
				pstmt.setString(3, range);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
			}
			//resultset
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
	

	//count
	public int selectClassCnt(String type) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql  = "SELECT COUNT(*) FROM CLASS WHERE TYPE = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);

			// resultset
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ClassVO cvo = new ClassVO();
				
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);
		}
		return result;
	}
	
	//pre-class page : favorite update 
	public int updateFavorite(int classId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "UPDATE CLASS SET FAVORITE = ("
				+ "        SELECT COUNT(*) FROM FAVORITE"
				+ "        WHERE CLASSID = ?)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classId);
			
			result = pstmt.executeUpdate();
			
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
		String sql1 = "INSERT INTO CLASS(CLASSID, CLASSNAME, CREATER, CLASSTYPE, DETAIL, PRICE, CAPACITY, ADDRESS, TYPE, CATEGORY, OPENDATE)"
				+ "	   VALUES((SELECT COUNT(*)FROM CLASS)+1, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE+7)";
		
		//비대면
		String sql2 = "INSERT INTO CLASS(CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, DETAIL, PRICE, TYPE, CATEGORY, OPENDATE)"
				+ "	   VALUES((SELECT COUNT(*)FROM CLASS)+1, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE+7)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			if(classType.equals("ON")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, cvo.getClassName());
				pstmt.setString(2, cvo.getCreater());
				pstmt.setString(3, cvo.getClassType());
				pstmt.setString(4, cvo.getDetail());
				pstmt.setInt(5, cvo.getPrice());
				pstmt.setInt(6, cvo.getCapacity());
				pstmt.setString(7, cvo.getAddress());
				pstmt.setString(8, "P");
				pstmt.setString(9, cvo.getCategory());
			}else if(classType.equals("OFF")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, cvo.getClassName());
				pstmt.setString(2, cvo.getCreater());
				pstmt.setString(3, cvo.getClassType());
				pstmt.setInt(4, cvo.getPeriod());
				pstmt.setString(5, cvo.getDetail());
				pstmt.setInt(6, cvo.getPrice());
				pstmt.setString(7, "P");
				pstmt.setString(8, cvo.getCategory());
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
	
	public Date selectDate() {
		
		Connection conn = DBConnect.getInstance();
		
		String sql = "SELECT OPENDATE FROM CLASS";
		
		Statement stmt = null;
		ResultSet rs = null;
		Date openDate = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return openDate;
	}
}
