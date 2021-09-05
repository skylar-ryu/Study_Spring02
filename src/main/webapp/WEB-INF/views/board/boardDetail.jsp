<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardDetail **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring MVC2 BoardDetail **</h2>
<table>
	<tr height="40"><td bgcolor="SkyBlue">Seq</td>
		<td>${Apple.seq}</td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Id</td>
		<td>${Apple.id}</td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Title</td>
		<td>${Apple.title}</td></tr>	
	<tr height="40"><td bgcolor="SkyBlue" >Content</td>
		<td><textarea rows="10" cols="40" readonly>${Apple.content}</textarea></td>
	</tr>
	<tr height="40"><td bgcolor="SkyBlue">Regdate</td>
		<td>${Apple.regdate}</td></tr>
	<tr height="40"><td bgcolor="SkyBlue">Count</td>
		<td>${Apple.cnt}</td></tr>
</table>

<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<br><hr>
<!--** 로그인 했는지 ..
 	** 글쓴이(Apple.id) 와 글보는이(loginID) 가 동일인 인지 .. 
 	=> 표시 되는 메뉴가 달라져야 함 
 	=> 답글 달기 추가 -->
<c:if test="${loginID!=null}"> 	
  <c:if test="${loginID==Apple.id || loginID=='admin'}">
	<a href="bdetail?seq=${Apple.seq}&jcode=U">글수정</a>&nbsp;
	<a href="bdelete?seq=${Apple.seq}">글삭제</a>&nbsp;
  </c:if>
	<a href="binsertf">새글등록</a>&nbsp;
	<a href="replyf?root=${Apple.root}&step=${Apple.step}&indent=${Apple.indent}">답글등록</a>&nbsp;
	<a href="logout">Logout</a>&nbsp;
	<br><hr>
</c:if>  
<a href="blist">BList</a>&nbsp;
<a href="home">HOME</a>
</body>
</html>