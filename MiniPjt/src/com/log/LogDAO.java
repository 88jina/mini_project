package com.log;

import java.sql.*;

public class LogDAO {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String uid = "jina";
	static String upw = "password";
	static Connection con;
	static PreparedStatement st;
	static ResultSet rs;
	
	public LogDAO() {
		try {
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection(url,uid,upw);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			con.setCatalog("parking");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//입차로그 데이터 넣기
	public void in(String car_num) {
		
		try {
			String query = "INSERT INTO inLogs (car_num) VALUES(?)";
			
			st=con.prepareStatement(query);
			st.setString(1, car_num);
			st.executeUpdate();
			
			System.out.println("done");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	//멤버십 체크해서 데이터업데이트
	public void insert(String car_num, int result) {
		String query2="UPDATE inLogs SET is_mem=? WHERE car_num=?";
		try {
			st=con.prepareStatement(query2);
			st.setInt(1, result);
			st.setString(2, car_num);
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println("done");
	}
	
	
	//이미 입차된 차량인지 확인
	public String select(String car_num) {
		String callback=null;
		try {
			String query="SELECT car_num FROM inLogs WHERE car_num=?";
			
			st = con.prepareStatement(query);
			st.setString(1, car_num);
			rs =st.executeQuery();
			
			while(rs.next()) {
				callback=rs.toString();
			}
			
			System.out.println("done");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return callback;
	}

	//출차하면 입차로그에서 지우기
	public void delete(String car_num) {
		String query ="DELETE FROM inLogs WHERE car_num=?";
		
		try {
			st= con.prepareStatement(query);
			st.setString(1, car_num);
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	//입차로그에 멤버십찍혔는지 확인
	public int checkMem(String car_num) {
		String query ="SELECT is_mem FROM inLogs WHERE car_num=?";
		int member=1;
		try {
			st = con.prepareStatement(query);
			st.setString(1, car_num);
			rs =st.executeQuery();
			
			while(rs.next()) {
				member=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
			return member;	
	}
	
	
	public void close() {
		if(rs!=null) {
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
