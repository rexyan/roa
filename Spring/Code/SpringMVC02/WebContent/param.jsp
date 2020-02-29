<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form action="param" method="POST">
		username: <input type="text" name="name"/> <br/> 不传递 name 参数
		password: <input type="text" name="password"/> <br/>
		age: <input type="text" name="age"/> <br/>
		address: <input type="text" name="address"/> <br/>
		province: <input type="text" name="province"/> <br/>
		city: <input type="text" name="city"/> <br/>
		country: <input type="text" name="country"/> <br/>
		 <input type="submit" value="添加"/>
	</form>
	
	<hr/>
	<form action="param2" method="POST">
		 <input type="submit" value="添加"/>
	</form>
	
	<hr/>
	<form action="param3" method="POST">
		 <input type="submit" value="添加"/>
	</form> 
	
	<hr/>
	<form action="param4" method="post">
		username:<input type="text" name="username" /><br />
		password:<input type="text" name="password" /><br />
		age:<input type="text" name="age" /><br />
		province:<input type="text" name="address.province" /><br />
		city:<input type="text" name="address.city" /><br />
		country:<input type="text" name="address.country" /><br />
		<input type="submit" value="添加" />
	</form>
	
</body>
</html>