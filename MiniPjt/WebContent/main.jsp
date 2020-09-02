<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/mainMedia.css">
</head>
<body>
 <div class="container">
        <header><p>주차관리 시스템</p></header>
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
                        <td><a href="#" onclick="button_click()">결제내역조회</a></td>
                    </tr>
                </table>
            </div>
            <div id="left">
                <form action="login" method="post">
                    <div class="loginbox">
                        <div class="inputbox" >
                            <p>ID</p> <input type="text" name="id" id="id"><br>
                            <p>PW</p> <input type="password" name="pw">
                        </div>
                        <div class="button">
                            <input type="submit" value="login">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <footer><p>구공 주차장</p></footer>

    </div>
       <script type="text/javascript">
    function button_click(){
        alert('관리자가 아닙니다');
    }
    </script>
</body>
</html>