package com.pay;

import java.sql.*;
import java.text.*;
import java.util.*;

public class PayDAO {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String uid = "jina";
	static String upw = "password";
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;

	// connect열기
	public PayDAO() {
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

	// 현재시간 출력
	public long nowTime() {
		long out_time = 0;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String now = formatter.format(cal.getTime());
		Timestamp Tout_time = Timestamp.valueOf(now);
		out_time = Tout_time.getTime();

		return out_time;
	}

	// 입차시간 출력
	public long checkInTime(String car_num) {
		long in_time = 0;

		Timestamp in_timeT = null;

		String query1 = "SELECT in_time FROM inLogs WHERE car_num=?";
		try {
			st = con.prepareStatement(query1);
			st.setString(1, car_num);
			rs = st.executeQuery();
			while (rs.next()) {
				in_timeT = rs.getTimestamp(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		in_time = in_timeT.getTime();

		return in_time;
	}

	// 주차시간계산
	public long calcTime(long in_time, long out_time) {

		long diffTime=0;
		diffTime = (in_time-out_time)/(1000*60*60);
		return diffTime;

	}

	// 요금계산
public long calcSum(long time) {
		if (time < 540) {
			long sum =time / 30 * 2500;
			return sum;
		} else {
			long sum = 50000;
			return sum;
		}
	}

//잔돈계산
	public long change(long sum, long received) {
		long given = received - sum;
		return given;
	}

//데이터저장
	public void insert(String car_num, int discount, long sum, long received, long given) {

		try {
			String query = "INSERT INTO pay (car_num, discount, sum, received, given) VALUES(?,?,?,?,?)";
			st = con.prepareStatement(query);
			st.setString(1, car_num);
			st.setInt(2, discount);
			st.setLong(3, sum);
			st.setLong(4, received);
			st.setLong(5, given);
			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	//결제내역 데이터 삭제
	public void delete(Timestamp pay_date) {
		String query ="DELETE FROM pay WHERE pay_date=?";
		try {
			st=con.prepareStatement(query);
			st.setTimestamp(1, pay_date);
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
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
