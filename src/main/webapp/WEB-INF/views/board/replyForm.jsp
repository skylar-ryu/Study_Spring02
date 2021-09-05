<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Reply Insert **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring MVC2 Reply Insert **</h2>
<table><form action="reply" method="get">
	<tr height="40"><td bgcolor="yellow">Id</td>
		<td><input type="text" name="id" value="${loginID}" readonly></td></tr>
	<tr height="40"><td bgcolor="yellow">Title</td>
		<td><input type="text" name="title"></td></tr>	
	<tr height="40"><td bgcolor="yellow" >Content</td>
		<td><textarea name="content" rows="10" cols="40"></textarea></td>
	</tr>
	<tr><td></td> 
		<!-- 답글등록시 필요한 부모글의 root, step, indent 전달 위함 -->
		<td><input type="text" name="root" value="${boardVO.root}" hidden>
			<input type="text" name="step" value="${boardVO.step}" hidden>
			<input type="text" name="indent" value="${boardVO.indent}" hidden>
		</td>
	</tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="전송">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</form></table>

<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<br><hr>
<a href="blist">BList</a>&nbsp;
<a href="home">HOME</a>
</body>
</html>