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
<link rel="stylesheet" href="css\searchInfoResult.css">
<title>아이디/비밀번호 찾기 결과</title>
</head>
<body>
<div>
<c:choose>
	<c:when test="${result!=null}">
		<c:choose>
		<c:when test="${mode=='1'}">
			<p class="title">아이디 찾기 결과</p>
			<p>귀하의 아이디는 [ <b>${result}</b> ] 입니다.</p>
		</c:when>
		<c:when test="${mode=='2'}">
			<p class="title">비밀번호 찾기 결과</p>
			<p>귀하의 비밀번호는 [ <b>${result}</b> ] 입니다.</p>
		</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<script>
			(function searchFail() {
				alert("입력하신 정보가 존재하지 않습니다. 다시 시도해주세요");
				document.location.href="searchInfo.do";
			})()
		</script>
	</c:otherwise>
</c:choose>
<a href="login.do" id="goLogin">로그인 하러가기</a>
</div>
</body>
</html>