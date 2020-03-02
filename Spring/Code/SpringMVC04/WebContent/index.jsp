<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			$.ajax({
				url:"testJson3",
				type:"POST",
				data: {},
				dataType:"json",
				success: function(msg){
					alert(msg)
				}
			})
		})
	})
</script>

<body>	
	<a href="testJson"> 测试 JSON </a> <br/>
	<a href="testJson2"> 测试 Java Object JSON </a> <br/>
	
	<input type="button" id="btn" value="测试 Ajax "/>
	
</body>
</html>