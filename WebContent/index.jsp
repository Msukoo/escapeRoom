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
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\indexo.css?x">
<title>Escape In Disney World</title>

</head>
<body>
<%--상단 메뉴바--%>
<header>
<div id="top_navi"> 
	<a href="index.jsp"><img src="image\logo2.png" alt="logo" id="logo"></a>
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
						<li class="menuli"><a href="admin_index.do" class="menu" id="goToPage">관리자페이지</a></li>
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


<%--홍보사진--%>
<div>
	<img src="image\main1.JPG" alt="middle" class="middle">
</div>
<div>
	<img src="image\main_notice.JPG" alt="middle" class="middle">
</div>
<div>
	<img src="image\main_who.JPG" alt="middle" class="middle">
</div>

<%--미니 탈출목록 --%>
<div>
	<table>	
		<c:forEach var="th" items="${thema}">
			<tr>
				<td><a href="#"><img src="#" alt="thema" id="thema_img"></a></td>
				<td><a href="#">${th.th_branch}</a></td>
				<td>${th.th_title}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="map">
<p id="where">[ 찾아오시는 길 ]</p>
<!-- * 카카오맵 - 지도퍼가기 -->
<!-- 1. 지도 노드 -->
<div id="daumRoughmapContainer1605668750185" class="root_daum_roughmap root_daum_roughmap_landing"></div>

<!--
	2. 설치 스크립트
	* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
-->
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

<!-- 3. 실행 스크립트 -->
<script charset="UTF-8">
	new daum.roughmap.Lander({
		"timestamp" : "1605668750185",
		"key" : "23z7x",
		"mapWidth" : "1880",
		"mapHeight" : "500"
	}).render();
</script>
</div>
<jsp:include page="escape_user/footer.jsp" flush="true"/>
</body>
</html>