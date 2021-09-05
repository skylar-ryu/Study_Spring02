<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 MemberList2 **</title>
<link rel="stylesheet" type="text/css" href="./myLib/myStyle.css">
</head>
<body>
<!-- ** 관리자 모드 만들기 
	=> id 를 클릭하면 해당 id의 detail 정보 표시하기
	=> 관리자 계정 등록 (admin , 12345) 
	=> 서블릿은 mdetail 활용  
-->
<h2>** Web MVC2 MemberList2_Admin **</h2>
<br>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<table border="1" width=800>
<tr height="30" bgcolor="lime">
	<th>ID</th><th>Password</th><th>Name</th><th>Level</th>
	<th>BirthDay</th><th>Point</th><th>Weight</th>
</tr>
<c:forEach var="list" items="${Banana}">
<tr height="30" align="center">
	<td><a href="/Web02/mdetail?id=${list.id}">${list.id}</a></td>
			<!-- /Web02/mdetail?id=banana -->
	<td>${list.password}</td><td>${list.name}</td><td>${list.lev}</td>
	<td>${list.birthd}</td><td>${list.point}</td><td>${list.weight}</td>
</tr>
</c:forEach>
</table>
<hr>
<a href="/Web02/index.jsp">HOME</a>
</body>
</html>