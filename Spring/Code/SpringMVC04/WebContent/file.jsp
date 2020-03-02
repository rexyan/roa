<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="down"> 下载图片 </a>
	<hr/>
	
	<form action="upload2" method="POST" enctype="multipart/form-data">
		头像：<input type="file" name="uploadFile"/>
		描述：<input type="text" name="desc"/>
		<input type="submit" value="上传"/>
	</form>
</body>
</html>