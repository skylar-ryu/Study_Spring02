<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 MemberList **</title>
</head>
<body>
<h2>** Spring MVC2 Test MemberList **</h2>
<br>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<!-- <img src="resources/image/bar.gif"><br> -->
<img src="/green/resources/image/bar.gif"><br>
<!-- member/list 사용시에는 절대 경로 -->
</body>
<table border="1" width=800>
<tr height="30" bgcolor="greenyellow">
	<th>ID</th><th>Password</th><th>Name</th><th>Level</th>
	<th>BirthDay</th><th>Point</th><th>Weight</th>
</tr>
<c:forEach var="list" items="${Banana}">
<tr height="30" align="center">
	<td>${list.id}</td><td>${list.password}</td><td>${list.name}</td><td>${list.lev}</td>
	<td>${list.birthd}</td><td>${list.point}</td><td>${list.weight}</td>
</tr>
</c:forEach>
</table>
<hr>
<a href="/green/home">HOME</a> <!-- member/list 사용시 경로 -->
</body>
</html>