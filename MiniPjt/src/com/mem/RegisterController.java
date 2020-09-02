package com.mem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/add")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String mem_car = request.getParameter("mem_car");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");

		MemDTO dto = new MemDTO();
		dto.setMem_car(mem_car);
		dto.setName(name);
		dto.setPhone(phone);

		MemDAO dao = new MemDAO();
		dao.addMem(mem_car, name, phone);
		System.out.println("done");
		dao.close();

		
		
		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null) {
			response.sendRedirect("main.jsp");
			
		}
		else {
			response.sendRedirect("adminMain.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
