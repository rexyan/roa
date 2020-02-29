<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="testREST/1001">测试 GET 请求</a>
	
	<hr/>
	<form action="testREST" method="POST">
		<input type="submit" value="测试 POST 请求"/>
	</form>
	
	<hr/>
	<form action="testREST" method="POST">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="测试 PUT 请求"/>
	</form>
	
	<hr/>
	<form action="testREST/1001" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="测试 DELETE 请求"/>
	</form>
	
</body>
</html>