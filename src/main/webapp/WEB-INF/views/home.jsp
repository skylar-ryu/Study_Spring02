<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>
<h1>
	Hello Spring MVC2 !!!  
</h1>

<P>* Start time : ${serverTime} </P>
<c:if test="${loginID != null}">
&nbsp; => ${loginName} 님 반갑습니다 :)<br>
</c:if>
<hr>
<c:if test="${message != null}">
  => ${message}<br>
</c:if>
<img src="resources/image/jerry01.gif" width="600">
<hr>
<img src="resources/image/bar.gif"><br>
<!-- 로그인 안 했을 때 -->
<c:if test="${loginID==null}">
	<a href="loginf" >Login</a>&nbsp;&nbsp;
	<a href="joinf" >Join</a>
</c:if>
<!-- 로그인 했을 때 -->
<c:if test="${loginID!=null}">
	<a href="mdetail" >MyInfo</a>&nbsp;&nbsp;
	<a href="logout">Logout</a>
</c:if>
<hr>
<a href="mlist">MList</a>&nbsp;&nbsp;
<a href="blist">BList</a><br>
<a href="logj">Log4J</a>&nbsp;&nbsp;
<a href="member/list">Member2</a>&nbsp;&nbsp;
<a href="member/memberList3">view생략</a>&nbsp;&nbsp;
<a href="member/listOracle">OracleTest</a>&nbsp;&nbsp;
</body>
</html>
