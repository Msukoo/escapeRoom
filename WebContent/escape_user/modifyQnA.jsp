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
<link rel="stylesheet" href="css\header.css?w">
<link rel="stylesheet" href="css\modifyBoard.css?a">
<title>QnA</title>
<script>
	function modifyQnA(){
		if(confirm("수정하시겠습니까?")==true){
 			modifyForm.submit();
		 }else{
		     return false;
		 }
	}
</script>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>QnA</p>
</div>
<div id="contents">
<h2>질문 수정하기</h2>
<form name="modifyForm" method="post" action="modifyQnAOk.do">
	<table id="writeTb">
		<tr>
			<td>
				<input type="text" name="title" required value="${qnadto.qa_title}" id="titletd">
			</td>
		</tr>
		<tr>
			<td><textarea name="contents" cols="100" rows="15" id="textbx">${qnadto.qa_contents}</textarea></td>
		</tr>
		<tr>
			<td id="btntd"><input type="button" onclick="modifyQnA()" value="수정하기" id="submit_btn"></td>
		</tr>
	</table>
	<input type="hidden" name="no" value="${qnadto.qa_no}">
</form>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>