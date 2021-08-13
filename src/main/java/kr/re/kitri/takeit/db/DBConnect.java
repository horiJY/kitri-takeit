package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    // private static DBconnect singleton = null;
    private static Connection conn;

    public static Connection getInstance() {
		String user = "web_mini";
		String password = "web_mini";
	//	조장DB
		String url = "jdbc:oracle:thin:@14.36.28.131:1521:xe";
	//	개발은 로컬에서
	//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
		try {
		    if (conn != null && !conn.isClosed()) {
			return conn;
		    }
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection(url, user, password);
		    System.out.println("DB연결완료");
		} catch (ClassNotFoundException e) {
		    System.out.println("ojdbc.jar 존재 여부 확인)");
		    // e.printStackTrace();
		} catch (SQLException e) {
		    System.out.println("url,user,password 확인");
		    // e.printStackTrace();
		}
		return conn;
    }

    // public static DBconnect getInstance() {
    // if(singleton == null) {
    // singleton = new DBconnect();
    // }
    //
    // return singleton;
    // }

}
