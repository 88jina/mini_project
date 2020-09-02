package com.out;

import java.sql.*;

public class OutDAO {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String uid = "jina";
	static String upw = "password";
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;

	public OutDAO() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, uid, upw);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			con.setCatalog("parking");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
	//결제했는지 확인
	public boolean check(String car_num) {
		boolean payed = false;
		String query = "SELECT car_num FROM pay WHERE car_num=?";
		try {
			st = con.prepareStatement(query);
			st.setString(1, car_num);
			rs = st.executeQuery();
			if(rs.next()) {
				payed=true;
			}
			else {
				payed=false;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return payed;
	}

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet 예외:");
				e.printStackTrace();
			}
		}
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement 예외:");
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Connection 예외:");
				e.printStackTrace();
			}
	}
}
