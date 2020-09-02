<%@page import="java.io.PrintWriter"%>
<%@page import="com.pay.PayDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/default.css">
</head>

<body>
    <div class="container">
        <div>

            <form action="calc" method="get" onsubmit="return emptyCheck()">

                차량번호 <input id="car_num" type="text" name="car_num"> 
                <input type="submit" name="" value="조회">
                <%String temp = (String)request.getAttribute("pay");
int pay=0;
if(temp!=null){
 pay = Integer.parseInt(temp);}%>
                <p><%=pay%>원입니다. 0원인 경우 바로 출차하세요</p>
                
              
            </form>
            
           
            <form action="pay">
            차량번호 <input type="text" name="car_num"><br>
                받은 돈 <input id="cash" type="text" name="received" onsubmit="return payCheck()">직원 할인<input type="checkbox" name="discount" value="staff"><br> <input type="submit" value="결제"><br>
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

        function payCheck() {
            var cash = document.getElementById('cash').value;
            var pay = '<%= request.getAttribute("pay") %>';

            if (cash < pay) {
                alert('금액이 부족합니다');
                return false;
            }
            return true;
        }

    </script>
</body>

</html>
