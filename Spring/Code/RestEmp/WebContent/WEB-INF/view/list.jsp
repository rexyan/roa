<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示员工信息</title>
</head>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/index_work.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function () {
		$(".del").click(function () {
			//submit()将所获得的form元素提交
			if(confirm("确认删除吗？")){
				$("form").attr("action", this.href).submit();//$(this).attr("href")
				return false;
			}
			return false;//将超链接的默认行为取消
		});
	});//预加载函数或文档就绪函数
</script>

<body>
	<table>
		<tr>
			<td> ID </td>
			<td> LASTNAME </td>
			<td> EMAIL </td>
			<td> GENDER </td>
			<td> DEPARTMENT </td>
			<td> OPTION (<a href="emp"> ADD </a>)</td>
		</tr>
		<c:forEach items="${ emps }" var="emp"> 
			<tr>
				<td> ${emp.id} </td>
				<td> ${emp.lastName} </td>
				<td> ${emp.email} </td>
				<td> ${emp.gender==0?'女':'男'} </td>
				<td> ${emp.department.departmentName} </td>
				<td> 
					<a href="emp/${emp.id }">UPDATE</a>
					<a class="del" href="emp/${emp.id }">DELETE</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 删除表单 -->
	<form method="post">
		<input type="hidden" name="_method" value="DELETE" />
	</form>
	
</body>
</html>