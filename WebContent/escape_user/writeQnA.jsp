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
<link rel="stylesheet" href="css\writeBoard.css">
<script>
	function writeQnA(){
		if(confirm("작성하시겠습니까?")==true){
			writeForm.submit();
		 }else{
		     return false;
		 }
	}
</script>
<title>QnA</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>QnA</p>
</div>
<div id="contents">
<h2>질문하기</h2>
<form name="writeForm" method="post" action="writeQnAOk.do">
	<table id="rplyTb">
		<tr>
			<td>
				<input type="text" id="titletd" name="title" required placeholder="제목">
				<c:if test="${idSession=='admin' }">
					<input type="checkbox" name="noti" value="Y" id="notibox"><b id="noti"> 공지로 등록</b>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><textarea id="contentstd" name="contents" rows="15" placeholder="질문글을 작성해주세요"></textarea></td>
		</tr>
		<tr>
			<td><input type="button" class="submit_btn" onclick="writeQnA()" value="등록하기"></td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${idSession}">
</form>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>