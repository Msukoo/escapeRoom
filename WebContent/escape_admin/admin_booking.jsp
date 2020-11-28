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
<link rel="stylesheet" href="css\admin_booking.css?p">
<link rel="stylesheet" href="css\admin_header.css?p">
<title>*관리자* Escape In Disney World</title>
<head>
</head>
<body>
<jsp:include page="admin_header.jsp" flush="true"/>
<div id="title">
<p>예약관리</p>
</div>
<div id="contents">
<h2>예약현황</h2>
<form method="post" action="admin_index.do">
<select name="t" class="choice">
	<option value="1" ${t eq "1"? "selected" : ""}>테마</option>
	<option value="2" ${t eq "2"? "selected" : ""}>예약자</option>
</select>
<input type="text" name="q" id="searchtxt" value="${q}">
<input type="submit" value="찾기" id="searchBtn">
</form>
<table id="bookingtb">
	<thead>
		<td>예약번호</td><td>날짜</td><td>테마</td><td>시간</td><td>예약자</td><td>결과</td><td>확인/트로피적립</td>
	</thead>
	<c:forEach var="b" items="${bookingList}">
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
			<td>
				<c:choose>
					<c:when test="${b.book_status eq '0'}">
						<form method="post" action="complete.do">
							<input type="hidden" name="no" value="${b.book_no}">
							<input type="hidden" name="id" value="${b.book_id}">
							<input type="hidden" name="status" value="1">
							<input type="submit" value="성공" class="OkBtn">
						</form>
						<form method="post" action="complete.do">
							<input type="hidden" name="no" value="${b.book_no}">
							<input type="hidden" name="id" value="${b.book_id}">
							<input type="hidden" name="status" value="2">
							<input type="submit" value="실패" class="failBtn">
						</form>
					</c:when>
					<c:otherwise><span id="statusOk">처리 완료</span></c:otherwise>
				</c:choose>
				
			<td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>