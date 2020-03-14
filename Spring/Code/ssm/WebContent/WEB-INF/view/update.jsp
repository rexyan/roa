<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/index_work.css" />
</head>
<body>
	
	<form:form action="${pageContext.servletContext.contextPath }/emp" method="post" modelAttribute="emp">
		<input type="hidden" name="_method" value="PUT" />
		<form:hidden path="eid"/>
		<table>
			<tr>
				<th colspan="2">UPDATE EMP INFO</th>
			</tr>
			<tr>
				<td>ENAME</td>
				<td>
					<form:input path="ename"/>
				</td>
			</tr>
			<tr>
				<td>AGE</td>
				<td>
					<form:input path="age"/>
				</td>
			</tr>
			<tr>
				<td>sex</td>
				<td>
					<form:radiobuttons path="sex" items="${sex }"/>
				</td>
			</tr>
			<tr>
				<td>DEPARTMENT</td>
				<td>
					<form:select path="dept.did" items="${deptList }" itemLabel="dname" itemValue="did"></form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="修改" />
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>