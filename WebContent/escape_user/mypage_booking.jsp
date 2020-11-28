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
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\header.css?o">
<link rel="stylesheet" href="css\mypage_booking.css?w">
<title>마이 페이지</title>
<script>
	function cancelOk(){
		if(confirm("취소하시겠습니까?")==true){
			 cancelForm.submit();
		 }else{
		     return false;
		 }
	}
</script>
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
			<td><a href="mypage_booking.do" id="current" >내 예약현황</a></td>
			<td><a href="mypage.do" class="other">내 정보</a></td>
			<td><a href="mypage_review.do" class="other">내가 쓴 리뷰</a></td>
		</tr>
	</table>
</div>
<div id="contents">
<h2>내 예약 보기</h2><br>
<table id="mybook_tb">
	<thead>
		<td>예약번호</td><td>날짜</td><td>테마명</td><td>시간</td><td>현황</td><td>리뷰</td>
	</thead>
	<c:choose>
		<c:when test="${myBookingList==null}">
			<tr>
				<td colspan="5">예약된 목록이 없어요!  <a href="booking.do" id="goBooking">예약하러가기</a></td>
			</tr>
		</c:when>
	<c:otherwise>
	<c:forEach var="book" items="${myBookingList}">
		<tr>
			<td>${book.book_no}</td>
			<td>${book.book_date}</td><td>${book.book_thema}</td><td>${book.book_time}:00</td>
			<td>
				<c:choose>
					<c:when test="${book.book_status eq '0'}">예약 완료</c:when>
					<c:when test="${book.book_status eq '1'}">탈출 성공</c:when>
					<c:when test="${book.book_status eq '2'}">탈출 실패</c:when>
					<c:when test="${book.book_status eq '3'}">예약 취소</c:when>
				</c:choose>
			</td>
			<td>
				<c:choose>
				<c:when test="${book.book_status eq '1' || book.book_status eq '2'}">
					<form method="post" action="writeReview.do">
						<input type="hidden" name="date" value="${book.book_date}">
						<input type="hidden" name="thema" value="${book.book_thema}">
						<input type="submit" value="리뷰쓰기" class="bookBtn">
					</form>
				</c:when>
				<c:when test="${book.book_status eq '3'}">
					취소 완료
				</c:when>
				<c:otherwise>
					<form name="cancelForm" method="post" action="cancelBooking.do" >
						<input type="hidden" name="no" value="${book.book_no}">
						<input type="submit" value="취소하기" class="bookBtn" id="cancle" onClick="cancelOk()">
					</form>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	</c:otherwise>
	</c:choose>
</table>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>