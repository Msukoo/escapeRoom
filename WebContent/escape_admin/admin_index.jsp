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
<link rel="stylesheet" href="css\admin_header.css?p">
<link rel="stylesheet" href="css\admin_index.css?o">
<title>*관리자* Escape In Disney World</title>
<head>
</head>
<body>
<jsp:include page="admin_header.jsp" flush="true"/>
<div id="title">
<p>TODAY</p>
</div>
<div id="contents">
<h2>오늘 탈출 일정</h2>
<table id="bookingtb">
	<thead>
		<td>예약번호</td><td>날짜</td><td>테마</td><td>시간</td><td>예약자</td><td>결과</td>
	</thead>
	<c:forEach var="b" items="${todayBookingList}">
		<tr>
			<td>${b.book_no}</td>
			<td>${b.book_date}</td>
			<td id="thematd">${b.book_thema}</td>
			<td>${b.book_time}:00</td>
			<td>${b.book_id}</td>
			<td>
				<c:choose>
					<c:when test="${b.book_status eq '0'}"><span style='color:green'>예약 완료</span></c:when>
					<c:when test="${b.book_status eq '1'}"><span style='color:#4641D9'>탈출 성공</span></c:when>
					<c:when test="${b.book_status eq '2'}"><span style='color:red'>탈출 실패</span></c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
<h2 id="second">오늘 가입 회원</h2>
<table id="usertb">
	<thead>
		<td>아이디</td>
		<td>이름</td>
		<td>이메일</td>
		<td>비밀번호</td>
		<td>핸드폰</td>
		<td>선호타입</td>
		<td>가입날짜</td>
	</thead>
	<c:forEach var="u" items="${todayuserList}">
		<tr>
			<td>${u.user_id}</td>
			<td>${u.user_name}</td>
			<td>${u.user_email}</td>
			<td>${u.user_pw}</td>
			<td>${u.user_phone}</td>
			<td>${u.user_prefer}</td>
			<td>${u.user_jDate}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>