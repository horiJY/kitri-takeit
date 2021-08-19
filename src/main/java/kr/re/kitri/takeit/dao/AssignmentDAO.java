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

public class AssignmentDAO {
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
	
	//강의 신청 페이지 -> Insert(등록)
	
	
	//mypage -> select assignment class
		public List<ClassVO> selectAssignmentClass(String id){
			Connection conn = DBConnect.getInstance();
			String sql = "SELECT CLASSNAME, CREATER, CLASSTYPE, RECOMMEND, CATEGORY FROM CLASS"
						+ " WHERE CLASSID IN (SELECT CLASSID FROM ASSIGNMENT WHERE USERID = '" + id + "')"
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
	//마이페이지 -> Delete(수강 취소)
	
}
