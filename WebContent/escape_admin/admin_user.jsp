<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Minsu">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\admin_header.css?q">
<link rel="stylesheet" href="css\admin_user.css?q">
<title>*관리자* Escape In Disney World</title>
</head>
<body>
<jsp:include page="admin_header.jsp" flush="true"/>
<div id="title">
<p>회원관리</p>
</div>
<div id="contents">
<h2>회원명단</h2>
<form method="post" action="admin_user.do">
<select name="t" class="choice">
	<option value="1" ${t eq 1? "selected":"" }>아이디</option>
	<option value="2" ${t eq 2? "selected":"" }>이름</option>
</select>
<input type="text" name="q" id="searchtxt" value="${q}">
<input type="submit" value="찾기" id="searchBtn">
</form>
<table id="usertb">
	<thead>
		<td>아이디</td>
		<td>이름</td>
		<td>이메일</td>
		<td>비밀번호</td>
		<td>핸드폰</td>
		<td>선호타입</td>
		<td>가입날짜</td>
		<td>트로피</td>
		<td>트로피관리</td>
	</thead>
	<c:forEach var="u" items="${userList}">
		<tr>
			<td>${u.user_id}</td>
			<td>${u.user_name}</td>
			<td>${u.user_email}</td>
			<td>${u.user_pw}</td>
			<td>${u.user_phone}</td>
			<td>${u.user_prefer}</td>
			<td>${u.user_jDate}</td>
			<form method="post" action="modifyTrophy.do">
			<input type="hidden" name="id" value="${u.user_id}">
			<td><input type="number" name="trophy" value="${u.user_trophy}" id="num"></td>
			<td><input type="submit" value="수정하기" id="modifyBtn">
			<td>
			</form>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>