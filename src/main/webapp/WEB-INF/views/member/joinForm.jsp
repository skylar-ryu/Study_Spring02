<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 MemberJoin Form **</title>
</head>
<body>
<h2>** Spring MVC2 MemberJoin Form **</h2>
<table><form action="join" method="get">
	<tr height="40"><td bgcolor="aqua">I  D</td>
		<td><input type="text" name="id" size="20"></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Password</td>
		<td><input type="password" name="password" size="20"></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Name</td>
		<td><input type="text" name="name" size="20"></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Level</td>
		<td><select name="lev">
				<option value="A">관리자</option>
				<option value="B">나무</option>
				<option value="C">잎새</option>
				<option value="D" selected>새싹</option>
		</select></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Birthday</td>
		<td><input type="date" name="birthd"></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Point</td>
		<td><input type="text" name="point" size="20"></td>
	</tr>
	<tr height="40"><td bgcolor="aqua">Weight</td>
		<td><input type="text" name="weight" size="20"></td>
	</tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="가입">&nbsp;&nbsp;
			<input type="reset" value="취소"></td>
	</tr>
</form></table>
<c:if test="${message != null}">
	<br>${message}<br><br>	
</c:if>
<hr>
<a href="home">HOME</a>
</body>
</html>