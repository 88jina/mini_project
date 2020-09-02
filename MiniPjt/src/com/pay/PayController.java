package com.pay;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.log.*;
import com.mysql.cj.util.*;

@WebServlet("/pay")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PayController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String car_num = request.getParameter("car_num"); // 차량번호
		String received_s = request.getParameter("received"); // 받은돈
		String[] staff = request.getParameterValues("discount"); // 직원할인 여부
		int discount = 0;
		boolean blank = StringUtils.isNullOrEmpty(received_s); //받은 돈에 아무것도 안썼을때
		try {
			if (blank) {
				received_s = "0";
			}

			long received = Long.parseLong(received_s); // 연산 위해서 숫자 타입으로 바꿔줌

			PayDTO dto = new PayDTO(); // DTO객체 생성
			dto.setCar_num(car_num);
			dto.setReceived(received);

			LogDAO log = new LogDAO();
			String car = log.select(car_num);
			if (car != null) {

				PayDAO dao = new PayDAO(); // DAO 객체 생성
				long in_time = dao.checkInTime(car_num); // 입차시간
				long out_time = dao.nowTime(); // 출차시간(현재시간)
				long parkingTime = dao.calcTime(in_time, out_time); // 주차시간
				long sum = dao.calcSum(parkingTime); // 요금계산
				
				request.setAttribute("sum", sum);// 요금 출력하려고 저장
				
				// 직원할인 설정
				if (staff != null) {
					sum /= 2;
					discount = 1;
				}

				long change = dao.change(sum, received); // 잔돈계산

				if (change < 0) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script type='text/javascript'>");
					writer.println("alert('금액이 부족합니다')");
					writer.println("history.back();");
					writer.println("</script>");
					writer.flush();
					return;
				} else {
					dao.insert(car_num, discount, sum, received, change); // 모든 데이터를 디비에 저장

					System.out.println("done");
				}
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('입차되지 않은 차량입니다')");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				return;
			}
		}

		catch (NumberFormatException e) {
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("main.jsp");

		} else {
			response.sendRedirect("adminMain.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
