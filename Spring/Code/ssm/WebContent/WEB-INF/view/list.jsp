<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示员工信息</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/index_work.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("#selectAll").click(function () {
			$("[name='eid']").prop("checked", $(this).prop("checked"));
		});
		
		$("#deleteMore").click(function () {
			$("form").attr("action", $(this).attr("href")).submit();
			return false;
		});
	});
</script>
</head>
<body>
	<form method="post">
		<input type="hidden" name="_method" value="DELETE" />
		<table>
			<tr>
				<th>
					<input type="checkbox" id="selectAll" />
				</th>
				<th>EID</th>
				<th>ENAME</th>
				<th>AGE</th>
				<th>SEX</th>
				<th>DEPARTMENTNAME</th>
				<th>OPTIONS</th>
			</tr>
			<c:forEach items="${empList }" var="emp">
				<tr>
					<td>
						<input type="checkbox" name="eid" value="${emp.eid }" />
					</td>
					<td>${emp.eid }</td>
					<td>${emp.ename }</td>
					<td>${emp.age }</td>
					<td>${emp.sex == 0 ? '女':'男' }</td>
					<td>${emp.dept.dname }</td>
					<td>
						<a href="emp">删除</a>
						<a href="${pageContext.servletContext.contextPath }/emp/${emp.eid }">修改</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10">
					<a id="deleteMore" href="${pageContext.servletContext.contextPath }/emps">批量删除</a>&nbsp;&nbsp;
					${page }
				</td>
			</tr>
		</table>
	</form>

</body>
</html>