package com.mem;

import java.sql.*;

public class MemDAO {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String uid = "jina";
	static String upw = "password";
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;

	public MemDAO() {
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

	//멤버십가입, 데이터 저장
	public void addMem(String car_num, String name, String phone) {

		try {
			String query = "INSERT INTO membership (mem_car,name,phone) VALUES (?,?,?)";

			st = con.prepareStatement(query);

			st.setString(1, car_num);
			st.setString(2, name);
			st.setString(3, phone);

			st.executeUpdate();

			System.out.println("done");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//멤버인지 체크
	public int checkMem(String car_num) {
		int result = 0;
		try {
			String query1 = "SELECT mem_car FROM membership WHERE mem_car=?";
			st = con.prepareStatement(query1);
			st.setString(1, car_num);
			rs = st.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			System.out.println(result);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
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
