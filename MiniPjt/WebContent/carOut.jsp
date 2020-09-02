<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div>

			<form action="thanku" method="get" onsubmit="return emptyCheck()">

				차량번호 <input id="car_num" type="text" name="car_num"><br>
				<input type="submit" value="출차">
			</form>

		</div>
		<div>

			<form action="session" method="post">
				<input type="submit" value="홈으로">
			</form>
		</div>
	</div>
	<script>
		function emptyCheck() {
			var car_num = document.getElementById('car_num').value;
			if (car_num.length == 0) {
				alert('차량 번호를 입력해주세요');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>