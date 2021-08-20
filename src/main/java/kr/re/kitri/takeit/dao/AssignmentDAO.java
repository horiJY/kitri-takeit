package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import vo.AssignmentVO;
import vo.ClassVO;

public class AssignmentDAO {
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

		}
	}

	public AssignmentVO assignmentSelect(String id) {
		Connection conn = DBConnect.getInstance();

		String sql = "SELECT USERNAME, USERPOINT,USERPHONE FROM WEBUSER WHERE USERID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AssignmentVO av = null;

		try {
			pstmt = conn.prepareStatement(sql); // 위에 작성한 쿼리 가져옴>
			pstmt.setString(1, id);// 위 함수명에 적힌 String id
			rs = pstmt.executeQuery();// 작성한 쿼리를 rs 저장

			while (rs.next()) { // rs 안에

				av = new AssignmentVO(); // vo에 대한 변수명 지정 및 vo에 값 저장
				av.setUsername(rs.getString(1));
				av.setPoint(rs.getInt(2));
				av.setUserphone(rs.getString(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		return av;
	}

	public ClassVO assignmentPaySelect(int classid) {
		Connection conn = DBConnect.getInstance();
		String sql = "SELECT CLASSID,PRICE,SALE FROM CLASS WHERE CLASSID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ClassVO cv = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cv = new ClassVO();
				cv.setClassId(rs.getInt(1));
				cv.setPrice(rs.getInt(2));
				cv.setSale(rs.getInt(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cv;

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

}
