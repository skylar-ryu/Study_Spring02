<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>*** Web_MVC2 LoginForm **</title>
</head>
<body>
<h2>*** Web_MVC2 LoginForm_UserVO **</h2>
<br>
<table><form action="/Web02/logintest" method="get">
	<tr><td bgcolor="Lavendar ">I D</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr><td bgcolor="Lavendar ">Password</td>
		<td><input type="password" name="password" value="12345"></td>
	</tr>
	<tr height="50"><td></td>
		<td><input type="submit" value="Login">&nbsp;&nbsp;
			<input type="reset" value="Reset">
		</td>
	</tr>
</form></table>
<c:if test="${message != null}">
	<br>${message}<br><br>	
</c:if>
<hr>
<a href="/Web02/index.jsp">HOME</a>
</body>
</html>