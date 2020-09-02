package com.admin;

import java.sql.*;

public class AdminDAO {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String uid = "jina";
	static String upw = "password";
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;

	public AdminDAO() {
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

	
	//관리자 로그인 정보 맞는지 틀린지 확인
	public boolean isAdmin(String input_id, String input_pw) {
		boolean check = false;

		String id = " ";
		String pw = " ";
		String query = "SELECT * FROM admin";

		try {

			st = con.prepareStatement(query);
			rs = st.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
				pw = rs.getString(2);
			}
			if (id.equals(input_id) && pw.equals(input_pw)) {
				check = true;
			} else {
				check = false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return check;
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
