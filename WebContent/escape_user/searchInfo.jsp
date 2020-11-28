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
<link rel="stylesheet" href="css\searchInfo.css">
<title>아이디/비밀번호 찾기</title>
<script>
	function searchIdCheck(){
		var name = searchIdForm.name.value;
		var pw= searchIdForm.pw.value;
		
		if(name==""||pw==""){
			alert("이름과 비밀번호를 모두 입력해주세요")
		}else{
			searchIdForm.submit();
		}
	}
	
	function searchPwCheck(){
		var id = searchPwForm.id.value;
		var name= searchPwForm.name.value;
		
		if(id==""||name==""){
			alert("아이디과 이름을 모두 입력해주세요")
		}else{
			searchPwForm.submit();
		}
	}
</script>
</head>
<body>
<div id="box">
<div class="form">
<form name="searchIdForm" method="post" action="searchInfoOk.do">
<table>
	<thead>
		<td><p>아이디 찾기</p></td>
	</thead>
	<tr>
		<td class="titletd"><b>이름</b></td>
	</tr>
	<tr>
		<td><input type="text" name="name" class="inputbox"></td>
	</tr>
	<tr>
		<td class="titletd"><b>비밀번호</b></td>
	</tr>
	<tr>
		<td><input type="password" name="pw" class="inputbox"></td>
	</tr>
	<tr>
		<td><input type="button" value="아이디 찾기" onclick="searchIdCheck()" class="loginbtn"></td>
	</tr>
</table>
<input type="hidden" name="mode" value="1">
</form>
</div>
<div class="form">
<form name="searchPwForm" method="post" action="searchInfoOk.do">
<table>
	<thead>
		<td><p>비밀번호 찾기</p></td>
	</thead>
	<tr>
		<td class="titletd"><b>아이디</b></td>
	</tr>
	<tr>
		<td><input type="text" name="id" class="inputbox"></td>
	</tr>
	<tr>
		<td class="titletd"><b>이름</b></td>
	</tr>
	<tr>
		<td><input type="text" name="name" class="inputbox"></td>
	</tr>
	<tr>
		<td><input type="button" value="비밀번호 찾기" onclick="searchPwCheck()" class="loginbtn"></td>
	</tr>
</table>
<input type="hidden" name="mode" value="2">
</form>
</div>
</div>
</body>
</html>