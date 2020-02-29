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
					<a href="emp/${ emp.id }"> 修改 </a>
					<a href="emp"> 删除 </a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>