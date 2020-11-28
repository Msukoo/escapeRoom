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
<link rel="stylesheet" href="css\login.css?a">
<title>로그인</title>
<script>
	function loginCheck(){
		var id = loginForm.id.value;
		var pw= loginForm.pw.value;
		
		if(id==""||pw==""){
			alert("아이디와 비밀번호를 모두 입력해주세요")
		}else{
			loginForm.submit();
		}
	}
</script>
</head>
<body>
<c:if test="${login!=null&&!login}">

</c:if>
<div>
	<form name="loginForm" method="post" action="loginOk.do">
<table>
	<tr id="imgtr">
		<td><img src="image\logo2.png"></td>
	</tr>
	<tr>
		<td><b>아이디</b></td>
	</tr>
	<tr>
		<td><input type="text" name="id" class="inputbox"></td>
	</tr>
	<tr>
		<td> </td>
	</tr>
	<tr>
		<td><b>비밀번호</b></td>
	</tr>
	<tr>
		<td><input type="password" name="pw" class="inputbox"></td>
	</tr>
</table>
<c:choose>
<c:when test="${login!=null&&!login}">
<p id="noInfo">이메일이 없거나 비밀번호가 일치하지 않습니다.</p>
</c:when>
<c:when test="${login!=null&&login}">
<script>
(function loginSucess() { location.href="index.jsp" })()
</script>
</c:when>
</c:choose>
<br>
<input type="button" value="로그인" onclick="loginCheck()" id="loginbtn">
</form>
<a href="searchInfo.do" ><u><tt>아이디/비밀번호 찾기</tt></u></a> 
<span id="join">
	<a href="join.do" ><u><tt>회원이 아니세요?</tt></u></a>
</span>
</div>

</body>
</html>