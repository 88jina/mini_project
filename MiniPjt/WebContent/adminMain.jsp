<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/adminMain.css">
<link rel="stylesheet" href="css/adminMainMedia.css">
</head>
<body>
	<div>
		<header><p>주차관리 시스템</p></header>
		<div id="container">
		<div class="mainCon">
			<div id="main">
				<table>
					<tr>
						<td colspan="2"><a href="carIn.jsp">입차로그</a></td>
					</tr>
					<tr>
						<td><a href="Pay.jsp">결제</a></td>
						<td><a href="carOut.jsp">출차</a></td>
					</tr>
					<tr>
						<td><a href="Register.jsp">정기권가입</a></td>
						<td><a href="checkPayLog.jsp">결제내역조회</a></td>
					</tr>
				</table>
			</div>
			<div id="left">

				<div class="loginbox">
					<div class="inputbox">
						<p>관리자계정으로 로그인 하셨습니다.</p>
					</div>
					<div class="button">
						<form action="logout" method="get">
							<input type="submit" name="logout" value="로그아웃">
						</form>

					</div>
				</div>

			</div>
		</div>
		</div>
		<footer><p>구공 주차장</p></footer>

	</div>
</body>
</html>