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
<title>Insert title here</title>
</head>
<body>
<%--상단 메뉴바--%>
<header>
<div id="top_navi"> 
	<a href="index.jsp"><img src="image\logo.JPG" alt="logo" id="logo"></a>
		<ul id="menu">
			<li class="menuli"><a href="index.jsp" class="menu">Home</a></li>
			<li class="menuli"><a href="thema.do" class="menu">테마</a></li>
			<c:choose>
				<c:when test="${idSession!=null}">
					<li class="menuli"><a href="booking.do" class="menu">예약하기</a></li>
				</c:when>
				<c:otherwise>
					<li class="menuli"><a href="login.do" class="menu">예약하기</a></li>
				</c:otherwise>
			</c:choose>
			<li id="drop" class="menuli"><a href="#" class="menu">커뮤니티</a>
				<div class="sub_menu">
					<a href="review.do" class="comu_menu">탈출후기</a>
					<a href="QnA.do" class="comu_menu">QnA</a>
				</div>
			</li>
			<c:choose>
			<c:when test="${idSession!=null}">
				<c:choose>
					<c:when test="${idSession eq 'admin'}">
						<li class="menuli"><a href="..\admin_index.do" class="menu" id="goToPage">관리자페이지</a></li>
					</c:when>
					<c:otherwise>
						<li class="menuli"><a href="mypage.do" class="menu" id="goToPage">마이페이지</a></li>
					</c:otherwise>
				</c:choose>
				</ul>
				<span class="loginout">${idSession}님 <a href="logout.do" >로그아웃</a></span>
			</c:when>
			<c:otherwise>
				</ul>
				<a href="login.do" class="loginout">로그인/회원가입</a>
			</c:otherwise>
		</c:choose>
</div>
</header>
</body>
</html>