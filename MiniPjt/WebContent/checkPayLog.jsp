
<%@page import=" java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	  <%request.setCharacterEncoding("UTF-8") ;%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/paylog.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/?user=jina/parking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String uid = "jina";
	String upw = "password";
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;

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
%>

	<%
		String query = "SELECT * FROM pay";
		st = con.prepareStatement(query);
		rs = st.executeQuery();
		
	%>

	<div class="container">
		<table>

			<tr>
				<td>결제일</td>
				<td>차량번호</td>
				<td>직원할인</td>
				<td>주차요금</td>
				<td>받은 돈</td>
				<td>거스름 돈</td>
				<td>비고</td>
			</tr>
			<%
				while (rs.next()) {
			%>

			<tr>
				<td><%=rs.getTimestamp(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getInt(3)%></td>
				<td><%=rs.getLong(4)%></td>
				<td><%=rs.getLong(5)%></td>
				<td><%=rs.getLong(6)%></td>
				<td><a href="delete.jsp?pay_date=<%=rs.getTimestamp(1)%>">삭제</a></td>
			</tr>
			<%
				}
			%>
			<!-- while종료 -->

		</table>
	</div>

	<%
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	%>
	    <div>
        
        <form action="session" method="post">
            <input type="submit" value="홈으로">
        </form>
    </div>
</body>
</html>