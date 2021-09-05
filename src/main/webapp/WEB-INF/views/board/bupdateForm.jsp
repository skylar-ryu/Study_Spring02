<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 Board Update **</title>
<link rel="stylesheet" type="text/css" href="./myLib/myStyle.css">
</head>
<body>
<h2>** Web MVC2 Board Update **</h2>
<table><form action="/Web02/bupdate" method="get">
	<tr height="40"><td bgcolor="SkyBlue">Seq</td>
		<td><input type="text" name="seq" value="${Apple.seq}" readonly></td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Id</td>
		<td>${Apple.id}</td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Title</td>
		<td><input type="text" name="title" value="${Apple.title}"></td></tr>	
	<tr height="40"><td bgcolor="SkyBlue" >Content</td>
		<td><textarea name="content" rows="10" cols="40">${Apple.content}</textarea></td>
	</tr>
	<tr height="40"><td bgcolor="SkyBlue">Regdate</td>
		<td>${Apple.regdate}</td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Count</td>
		<td>${Apple.cnt}</td></tr>
	<tr height="40"><td></td>
		<td>&nbsp;<input type="submit" value="전송">&nbsp;&nbsp;
			&nbsp;<input type="reset" value="취소">
		</td>
	</tr>	
</form></table>

<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<br><hr>
<c:if test="${loginID!=null}"> 	
  <c:if test="${loginID==Apple.id || loginID=='admin'}">
	<a href="/Web02/bdelete?seq=${Apple.seq}">글삭제</a>&nbsp;
  </c:if>
	<a href="/Web02/board/binsertForm.jsp">새글등록</a>&nbsp;
	<a href="/Web02/logout">Logout</a>&nbsp;
	<br><hr>
</c:if>  
<a href="/Web02/blist">bList</a>&nbsp;
<a href="/Web02/index.jsp">HOME</a>
</body>
</html>