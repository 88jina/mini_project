package com.out;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.log.*;
import com.mem.*;
import com.pay.*;

@WebServlet("/thanku")
public class OutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OutController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String car_num = request.getParameter("car_num");

		PayDTO dto = new PayDTO();
		dto.setCar_num(car_num); // 데이터 객체화

		LogDAO log = new LogDAO();

		String car = log.select(car_num); //이미 입차된 차량인지 확인

		if (car!=null) {
			
			PayDAO pay = new PayDAO();
			
			long now = pay.nowTime();
			long in_time=pay.checkInTime(car_num); 
			long time=pay.calcTime(in_time, now);
			
		

			OutDAO dao = new OutDAO();
			boolean payed = dao.check(car_num); // 결제했는지 안했는지 확인

			int mem = log.checkMem(car_num); // 입차시 멤버였는지 확인

			if (payed || time<30) {
				log.delete(car_num);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('안녕히가세요')");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				return;

			} else if (!payed && mem != 1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('미결제차량입니다')");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				return;

			}

			else {

				log.delete(car_num);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('정기권 차량입니다. 안녕히가세요')");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				return;
			}
		}else {response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script type='text/javascript'>");
		writer.println("alert('입차되지 않은 차량입니다')");
		writer.println("history.back();");
		writer.println("</script>");
		writer.flush();
		return;
}

//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") != null) {
//			response.sendRedirect("adminMain.jsp");
//		} else {
//			response.sendRedirect("main.jsp");
//		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
