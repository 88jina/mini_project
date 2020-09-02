package com.admin;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class AdminController extends HttpServlet {
	
//	AdminDAO dependencyExample;
	
	private static final long serialVersionUID = 1L;

	public AdminController() {

		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		AdminDTO dto = new AdminDTO();
		dto.setId(id);
		dto.setPw(pw);

		AdminDAO dao = new AdminDAO();
		boolean admin = dao.isAdmin(id, pw);
		
		HttpSession session = request.getSession();
		if(admin) {
			session.setAttribute("id",id);
			session.setAttribute("pw", pw);
			
			response.sendRedirect("adminMain.jsp");
		}
		else{
			response.sendRedirect("checkLogin.jsp");
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
