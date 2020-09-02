package com.pay;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.log.*;

@WebServlet("/calc")
public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalcController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String car_num = request.getParameter("car_num");

		LogDAO log = new LogDAO();
		String isIn = log.select(car_num);
		if (isIn == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('입차되지 않은 차량입니다')");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			return;
		} else {
			PayDTO dto = new PayDTO();
			dto.setCar_num(car_num);

			PayDAO dao = new PayDAO();
			long out_time = dao.nowTime();
			long in_time = dao.checkInTime(car_num);
			long time = dao.calcTime(in_time, out_time);
			long sum = dao.calcSum(time);

			request.setAttribute("pay", sum);

			RequestDispatcher rd = request.getRequestDispatcher("Pay.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
