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

public class ClassDAO {
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
	
	//main page -> Select(className, creater, recommend, price, sale, classType)
	
	//class-detail page -> SelectAll
	
	//pre-class page -> Select(className, create, classType, favorite)
	
	//mypage -> select favorite class
	public List<ClassVO> selectFavoriteClass(String id){
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CREATER, FAVORITE, OPENDATE FROM CLASS "
				+ "WHERE CLASSID = (SELECT CLASSID FROM FAVORITE WHERE USERID = '" + id + "')"
				+ "AND TYPE='P'";
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
				cvo.setFavorite(rs.getInt(3));
				cvo.setOpenDate(rs.getDate(4));
				
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
	
	//mypage -> select assignment class
	
	public List<ClassVO> selectAssignmentClass(String id){
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CREATER, CLASSTYPE, RECOMMEND, CATEGORY  FROM CLASS"
					+ " WHERE CLASSID IN ((SELECT CLASSID FROM ART_ASSIGNMENT WHERE USERID = '" + id + "'),"
										+" (SELECT CLASSID FROM COOKING_ASSIGNMENT WHERE USERID = '" + id + "'),"
										+" (SELECT CLASSID FROM LANGUAGE_ASSIGNMENT WHERE USERID = '" + id + "'),"
										+" (SELECT CLASSID FROM PROGRAMMING_ASSIGNMENT WHERE USERID = '" + id + "'),"
										+" (SELECT CLASSID FROM SPORT_ASSIGNMENT WHERE USERID = '" + id + "'))"
					+ " AND TYPE='O'"
					+ " ORDER BY CATEGORY, CLASSID DESC";
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
				cvo.setRecommend(rs.getInt(4));
				cvo.setCategory(rs.getString(5));
				
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
	
	//mypage -> select my class
	
	public List<ClassVO> selectMyPreClass(String id){
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CATEGORY, FAVORITE, OPENDATE FROM CLASS"
				+ " WHERE CREATER = '" + id + "'"
				+ " AND TYPE='P'";
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ClassVO cvo = new ClassVO();
				cvo.setClassName(rs.getString(1));
				cvo.setCategory(rs.getString(2));
				cvo.setFavorite(rs.getInt(3));
				cvo.setOpenDate(rs.getDate(4));
				
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
	public List<ClassVO> selectMyOpenClass(String id){
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CATEGRY, RECOMMEND, CLASSTYPE FROM CLASS"
				+ " WHERE CREATER = '" + id + "'"
				+ " AND TYPE='O'";
		Statement stmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ClassVO cvo = new ClassVO();
				cvo.setClassName(rs.getString(1));
				cvo.setCategory(rs.getString(2));
				cvo.setRecommend(rs.getInt(3));
				cvo.setClassType(rs.getString(4));
				
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
	
	
	
	
	
}
