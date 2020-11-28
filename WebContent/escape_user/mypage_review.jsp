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
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\header.css?o">
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\mypage_review.css?q">
<title>마이 페이지</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>마이페이지</p>
</div>
<div class="contents" id="user">
	<table>
		<tr>
			<td><span id="name">${userdto.user_name}</span> 님</td>
			<td><img src="image\trophy_.png"> <span id="trophyNum">${userdto.user_trophy}</span> 개</td>
		</tr>
	</table>
</div>

<div class="contents" id="infomenu">
	<table>
		<tr>
			<td><a href="mypage_booking.do" class="other">내 예약현황</a></td>
			<td><a href="mypage.do" class="other">내 정보</a></td>
			<td id="currenttd"><a href="mypage_review.do" id="current">내가 쓴 리뷰</a></td>
		</tr>
	</table>
</div>
<div id="contents">
<h2>내 후기 보기</h2><br>
<table id="rev_tb">
	<thead>
		<td width="10%">테마</td>
		<td width="15%">제목</td>
		<td width="55%">후기</td>
		<td width="20%">작성일</td>
	</thead>
	<c:forEach var="revdto" items="${myReviewList}">
		<tr>
			<td>${revdto.rev_thema}</td>
			<td><a href="reviewView.do?no=${revdto.rev_no}">${revdto.rev_title}</a></td>
			<td class="contentstd">${revdto.rev_contents}</td>
			<td>${revdto.rev_wtime}</td>
		</tr>
	</c:forEach>
</table>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>