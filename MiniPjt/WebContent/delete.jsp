
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.pay.PayDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%request.setCharacterEncoding("UTF-8") ;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String pay_dates = request.getParameter("pay_date");
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
Date parsed = formatter.parse(pay_dates);

Timestamp pay_date = new Timestamp(parsed.getTime());
PayDAO dao=new PayDAO();
dao.delete(pay_date);
response.sendRedirect("checkPayLog.jsp");
%>
</body>
</html>