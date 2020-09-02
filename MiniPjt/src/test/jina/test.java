package test.jina;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.pay.*;

public class test {

	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String now = formatter.format(cal.getTime());
		Timestamp Tout_time = Timestamp.valueOf(now);
		
		System.out.println(Tout_time);
		
		PayDAO dao = new PayDAO();
		
		long out_time = dao.nowTime();
		System.out.println("현재시간:"+out_time);
		long in_time = dao.checkInTime("11A1114");
		System.out.println("입차시간 : "+in_time);
		
		long parkingTime=dao.calcTime(in_time, out_time);
		System.out.println("주차시간 : "+parkingTime);
		
		long sum = dao.calcSum(parkingTime);
		System.out.println("주차 요금: "+sum);
		
	}

}
