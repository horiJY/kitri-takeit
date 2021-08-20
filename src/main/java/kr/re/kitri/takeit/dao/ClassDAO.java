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
			// e.printStackTrace();
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
				+ " WHERE CLASSID IN (SELECT CLASSID FROM ASSIGNMENT WHERE USERID = '" + id + "')" + " AND TYPE='O'"
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
		String sql = "SELECT CLASSNAME, CATEGORY, FAVORITE, OPENDATE FROM CLASS" + " WHERE CREATER = '" + id + "'"
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

	// open
	public List<ClassVO> selectMyOpenClass(String id) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSNAME, CATEGRY, RECOMMEND, CLASSTYPE FROM CLASS" + " WHERE CREATER = '" + id + "'"
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

	// create class
	// classvo 확정되면 수정
	public int insertClass(ClassVO cvo) {
		Connection conn = DBConnect.getInstance();
		StringBuilder sb = new StringBuilder();
		// '부적합한 식별자'오류 발생
		sb.append(" INSERT INTO CLASS ( CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, ");
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

	// 강의 폐지
	// 삭제

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

	// detail page : class정보
	public ClassVO getClassDetail(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "select c.classname, u.username, c.introduce, c.period, c.content_num, c.detail, c.chapter, c.creater_info, c.address, c.classtype, c.category, c.recommend, c.price, c.sale, c.favorite "
				+ "from class c, webuser u " + "where c.creater = u.userid and classid = " + classId;

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
				cvo.setClassType(rs.getString(10));
				cvo.setCategory(rs.getString(11));
				cvo.setRecommend(Integer.parseInt(rs.getString(12)));
				cvo.setPrice(rs.getInt(13));
				cvo.setSale(rs.getInt(14));
				cvo.setFavorite(rs.getInt(15));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return cvo;
	}

	// 강의 이름 조회
	public String getClassName(int classId) {
		Connection conn = DBConnect.getInstance();

		String sql = "select classname" + "from class " + "where classid = " + classId;

		Statement stmt = null;
		ResultSet rs = null;

		String className = "";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				className = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		return className;
	}

	public int classUpdate(int favorite) {
		Connection conn = DBConnect.getInstance();
		// favorite >= 10
		String sql1 = "UPDATE CLASS SET TYPE = 'O' " + "WHERE TYPE = 'P'" + "AND FAVORITE >= 10"
				+ "AND OPENDATE <= SYSDATE";

		// favorite < 10
		String sql2 = "UPDATE CLASS SET TYPE = NULL " + "WHERE TYPE = 'P'" + "AND FAVORITE < 10"
				+ "AND OPENDATE <= SYSDATE";

		Statement stmt = null;
		int result = 0;

		try {
			stmt = conn.createStatement();
			if (favorite >= 10) {
				result = stmt.executeUpdate(sql1);
			} else {
				result = stmt.executeUpdate(sql2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, null);
		}
		return result;
	}

	// select
	public List<ClassVO> selectClassPage(String category, String range, String type, int start, int end) {
		if (range == null || range.equals("null")) {
			if (type.equals("O")) {
				range = "RECOMMEND";
			} else if (type.equals("P")) {
				range = "FAVORITE";
			}
		}

		// conn
		Connection conn = DBConnect.getInstance();

		// sql
		String sql1 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN, CLASS_THUMNAIL "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* " + "      FROM (SELECT * " + "            FROM CLASS"
				+ "			   WHERE CATEGORY = ? " + "			   AND TYPE = ? " + "			   ORDER BY " + range
				+ " DESC ) A " + "      ) " + "WHERE RNUM BETWEEN ? AND ? ";

		String sql2 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN, CLASS_THUMNAIL "
				+ " FROM (SELECT ROWNUM AS RNUM, A.* " + "      FROM (SELECT * " + "            FROM CLASS"
				+ "			   WHERE TYPE = ? " + "			   ORDER BY " + range + " DESC ) A " + "      ) "
				+ "WHERE RNUM BETWEEN ? AND ? ";

		// prepared
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();
		try {

			if (category == null || category.equals("null")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, type);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, category);
				pstmt.setString(2, type);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}

			// resultset
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				cvo.setClass_thumnail(rs.getString("CLASS_THUMNAIL"));
				// result
				clist.add(cvo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}
		return clist;
	}

	public List<ClassVO> selectClassList(String category, String range, String type) {
		Connection conn = DBConnect.getInstance();

		if (range == null || range.equals("null")) {
			range = "RECOMMEND";
		}

		String sql = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ "	  FROM CLASS " + "	  WHERE TYPE = ? " + "	  AND CATEGORY = ? " + "	  ORDER BY " + range
				+ " DESC";

		String sql2 = "SELECT CLASSID, CLASSNAME, CREATER, CLASSTYPE, PERIOD, RECOMMEND, DETAIL, PRICE, SALE, CAPACITY, TYPE, FAVORITE, CATEGORY, OPENDATE, CEIL((OPENDATE- SYSDATE)) AS COUNTDOWN "
				+ "	   FROM CLASS " + "	   WHERE TYPE = ? " + "	   ORDER BY " + range + " DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClassVO> clist = new ArrayList<ClassVO>();

		try {

			if (category == null || category.equals("null")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, type);
			} else {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(2, category);
				pstmt.setString(1, type);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
		} finally {
			closeAll(conn, pstmt, null, rs);
		}
		return clist;
	}

}
