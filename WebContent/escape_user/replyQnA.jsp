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
<link rel="stylesheet" href="css\replyQnA.css?a">
<title>QnA</title>
<script>
	function replyQnA(){
		if(confirm("작성하시겠습니까?")==true){
			replyForm.submit();
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
<h2>답변하기</h2>
<form name="replyForm" method="post" action="replyQnAOk.do">
	<table id="rplyTb">
		<tr>
			<td>
				<input type="text" id="titletd" name="title" value="┖  답변:${qnadto.qa_title}" readOnly>
			</td>
		</tr>
		<tr>
			<td><textarea id="contentstd" name="contents"  rows="15" placeholder="질문글을 작성해주세요">${qnadto.qa_contents}</textarea></td>
		</tr>
		<tr>
			<td><input type="button" class="submit_btn" onclick="replyQnA()" value="등록하기"></td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${idSession}">
	<input type="hidden" name="gNum" value="${qnadto.qa_groupNum}">
	<input type="hidden" name="sNum" value="${qnadto.qa_stepNum}">
	<input type="hidden" name="iNum" value="${qnadto.qa_indentNum}">
</form>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>