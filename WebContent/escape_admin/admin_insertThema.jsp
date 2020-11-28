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
<link rel="stylesheet" href="css\admin_insertThema.css?q">
<link rel="stylesheet" href="css\admin_header.css?q">
<script>
	function insertCheck(){
		 var name = insertForm.name.value;
		 var about = insertForm.about.value;
		 
		 if(name==""||about==""){
			alert("모든 칸을 정확히 채워주세요");
		 }else{
			 var answer = confirm("등록하시겠습니까?");
			 if(answer==true)
				 insertForm.submit();
			 else
				 return;
		 }
	}
</script>
<title>*관리자* 새로운 테마 추가</title>
</head>
<body>
<jsp:include page="admin_header.jsp" flush="true"/>
<div id="title">
<p>테마관리</p>
</div>
<div id="contents">
<h2>테마 추가하기</h2>
<form name="insertForm" method="post" action="insertThemaOk.do" enctype="multipart/form-data">
	<table id="insertTb">
		<tr>
			<td class="tbTitle">테마 이름</td>
			<td><input type="text" name="name" id="nameTxt"></td>
		</tr>
		<tr>
			<td class="tbTitle">테마 설명</td>
			<td><textarea name="about" cols="100" rows="10"></textarea></td>
		</tr>
		<tr>
			<td class="tbTitle">난이도</td>
			<td>
				<select name="level" id="sel">
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="tbTitle">사진</td>
			<td><input type="file" name="thImg"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" onclick="insertCheck()" value="등록하기" id="okBtn"></td>
		</tr>
	</table>
</form>
</div>

</body>
</html>