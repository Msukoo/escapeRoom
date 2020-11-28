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
<link rel="stylesheet" href="css\admin_header.css?w">
<link rel="stylesheet" href="css\admin_them.css?w">

<title>*관리자* Escape In Disney World</title>
</head>
<body>
<jsp:include page="admin_header.jsp" flush="true"/>
<div id="title">
<p>테마관리</p>
</div>
<div id="contents">
<h2>테마목록</h2>
<a href="inserThema.do" id="insertThema">새로운 테마 추가하기</a>
<c:forEach var="th" items="${themaList}">
<table class="themaTb">
	<tr>
		<td rowspan="6" class="th_img"><img src="${th.them_img}" class="th_img"></td>
	</tr>
	<tr>
		<td class="th_name">${th.them_name}</td>
	</tr>
	<tr>
		<td class="th_contents">코드넘버 : ${th.them_code}</td>
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
		<td><a href="modifyThema.do?code=${th.them_code}" class="mbtn">수정하기</a> 
		<a href="deleteThema.do?code=${th.them_code}" class="mbtn">삭제하기</a></td>
		
	</tr>
</table>
</c:forEach>

</div>
</body>
</html>