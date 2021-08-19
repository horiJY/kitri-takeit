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

	// closeAll
	public void closeAll(Connection conn, PreparedStatement pstmt,
			Statement stmt, ResultSet rs) {
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
			// e.printStackTrace();
		}

	}

	// class-detail page -> SelectAll
	public List<ClassVO> selectDetail(int classId) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSID, CLASSNAME, CREATER, FAVORITE, OPENDATE FROM CLASS "
				+ "WHERE CLASSID = (SELECT CLASSID FROM FAVORITE WHERE USERID = '"
				+ classId + "')" + "AND TYPE='P'";
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

	// mypage -> select assignment class
	public List<ClassVO> selectAssignmentClass(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CREATER, CLASSTYPE, RECOMMEND, CATEGORY FROM CLASS"
				+ " WHERE CLASSID IN (SELECT CLASSID FROM ASSIGNMENT WHERE USERID = '"
				+ id + "')" + " AND TYPE='O'"
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

	// mypage -> select my class
	// pre
	public List<ClassVO> selectMyPreClass(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CATEGORY, FAVORITE, OPENDATE FROM CLASS"
				+ " WHERE CREATER = '" + id + "'" + " AND TYPE='P'";
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
	// open
	public List<ClassVO> selectMyOpenClass(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CATEGRY, RECOMMEND, CLASSTYPE FROM CLASS"
				+ " WHERE CREATER = '" + id + "'" + " AND TYPE='O'";
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

	// create class
	// classvo 확정되면 수정
	public int insertClass(ClassVO cvo) {
		Connection conn = DBConnect.getInstance();
		StringBuilder sb = new StringBuilder();
		// '부적합한 식별자'오류 발생
		sb.append(
				" INSERT INTO CLASS ( CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, ");
		sb.append(" DETAIL, PRICE, CAPACITY, TYPE, CATEGORY, INTRODUCE ) ");
		sb.append(" VALUES ( ( SELECT COUNT(*)+1 FROM CLASS ), '");
		sb.append(cvo.getClassName());
		sb.append("', '");
		sb.append(cvo.getCreater());
		sb.append("', '");
		sb.append(cvo.getClassType());
		sb.append("', ");
		sb.append(cvo.getPeriod());
		sb.append(", '");
		sb.append(cvo.getDetail());
		sb.append("', ");
		sb.append(cvo.getPrice());
		sb.append(", ");
		sb.append(cvo.getCapacity());
		sb.append(", '");
		sb.append(cvo.getType());
		sb.append("', '");
		sb.append(cvo.getCategory());
		sb.append("', '");
		sb.append(cvo.getIntroduce());
		sb.append("') ");
		String sql = sb.toString();
		Statement stmt = null;
		int result = 0;
		int classid = 0;
		try {
			stmt = conn.createStatement();
			// set prepared statement

			// get result
			result = stmt.executeUpdate(sql);
			if (result == 1) {
				classid = countClass();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}
		return classid;
	}

	// get classid
	public int countClass() {
		Connection conn = DBConnect.getInstance();
		String sql = " SELECT COUNT(*) FROM CLASS ";
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			stmt = conn.createStatement();

			// get result
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return count;
	}

	// 강의 페지
	public int closeClass(int classid) {
		Connection conn = DBConnect.getInstance();
		// 폐지상태로 변경
		String sql = " UPDATE CLASS SET TYPE = 'C' WHERE CLASSID = '" + classid
				+ "' ";
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();

			// get result
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}
		return result;
	}

	// detail page : class정보
	public ClassVO getClassDetail(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "select classname, u.username, introduce, period, content_num, detail, chapter, creater_info, address "
				+ "from class c, webuser u "
				+ "where c.creater = u.userid and classid = " + classId;

		Statement stmt = null;
		ResultSet rs = null;

		ClassVO cvo = new ClassVO();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cvo.setClassName(rs.getString(1));
				cvo.setClassName(rs.getString(1));
				cvo.setCreater(rs.getString(2));
				cvo.setIntroduce(rs.getString(3));
				cvo.setPeriod(rs.getInt(4));
				cvo.setContent_num(rs.getInt(5));
				cvo.setDetail(rs.getString(6));
				cvo.setChapter(rs.getString(7));
				cvo.setCreater_info(rs.getString(8));
				cvo.setAddress(rs.getString(9));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return cvo;
	}
}
