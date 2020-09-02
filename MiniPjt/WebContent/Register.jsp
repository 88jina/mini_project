<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/default.css">
</head>
<body>
 <div>
        <form action="add" method="get"  onsubmit="return emptyCheck()">
    차량번호<input id="car_num"type="text" name="mem_car"><br>
    이름<input type="text" name="name"><br>
    전화번호 <input type="text" name="phone"><br>
    <input type="submit" value="register">
    </form>
    </div>
        <div>
        
        <form action="session" method="post">
            <input type="submit" value="홈으로">
        </form>
    </div>
    
    <script>
    function emptyCheck(){
    	  var car_num = document.getElementById('car_num').value;
    	  if(car_num.length==0){
    		  alert('차량 번호를 입력해주세요');
    		  return false;
    	  }
    	  return true;
    	  }
    </script>
</body>
</html>