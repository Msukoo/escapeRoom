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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\header.css?o">
<link rel="stylesheet" href="css\thema.css?a">
<title>테마</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>테마</p>
</div>
<div id="contents">
<h2>테마목록</h2>
<c:forEach var="th" items="${themaList}">
<table class="themaTb">
	<tr>
		<td rowspan="6" class="th_img"><img src="${th.them_img}" class="th_img"></td>
	</tr>
	<tr>
		<td class="th_name">${th.them_name}</td>
	</tr>
	<tr>
		<td class="th_contents">${th.them_about}</td>
	</tr>
	<tr>
		<td class="th_contents">난이도<br>
			<c:forEach var="j" begin="1" end="${th.them_level}" step="1">
				<img src="image\star.png" class="star">
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td>
		<c:choose>
			<c:when test="${idSession!=null}">
					<a href="booking.do" class="mbtn"><input type="button" value="예약하기" class="go_btn"></a>
			</c:when>
			<c:otherwise>
				<a href="login.do" class="mbtn">예약하기</a>
			</c:otherwise>
		</c:choose>
			<a href="review.do?t=1&q=${th.them_name}" class="mbtn"><input type="button" value="리뷰보기" class="go_btn"></a>
		</td>
		
	</tr>
</table>
</c:forEach>

</div>

<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>