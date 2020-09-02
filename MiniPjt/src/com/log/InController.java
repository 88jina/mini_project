package com.log;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem.*;

@WebServlet("/welcome")
public class InController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogDAO dao = new LogDAO();

		String car_num = request.getParameter("car_num"); // 입력된 차량번호 받아오기

		String callback = dao.select(car_num);

		if (callback != null) {
			response.sendRedirect("alreadyIn.jsp");
		} else {
			LogDTO dto = new LogDTO();
			dto.setCar_num(car_num); // 이게 무슨 의민지 모르겠지만 데이터객체..뭐 이렇게하는거

			dao.in(car_num); // 입차로그 찍기

			MemDAO check = new MemDAO();
			int result = check.checkMem(car_num); //정기권 체크

			dao.insert(car_num, result); //데이터넣기

			
			
			
			
			HttpSession session = request.getSession();
			if (session.getAttribute("id") == null) {
			
				response.sendRedirect("main.jsp");

			} else {

				response.sendRedirect("adminMain.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
