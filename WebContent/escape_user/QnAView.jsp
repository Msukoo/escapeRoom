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
<link rel="stylesheet" href="css\header.css?p">
<link rel="stylesheet" href="css\QnAView.css?w">
<title>QnA</title>
<script>
 	function deleteQna(){
 		if(confirm("삭제하시겠습니까?")==true){
 			deleteForm.submit();
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
<h2>QnA 게시물</h2>
	<table id="viewTb">
		<tr>
			<td id="titletd">${qnadto.qa_title}</td>
		</tr>
		<tr>
			<td id="idtd">${qnadto.qa_id} ${qnadto.qa_wTime}</td>
		</tr>
		<tr>
			<td colspan="2" id="contentstd">${qnadto.qa_contents}</td>
		</tr>
		<tr>
			<td id="btnTd">
				<a href="QnA.do" ><input type="button" value="목록으로" id="back_btn"></a>
				<c:if test="${idSession==qnadto.qa_id ||idSession=='admin'}">
					<form name="deleteForm" method="post" action="deleteQnA.do">
						<input type="hidden" value="${qnadto.qa_no}" name="no"> 
						<input type="button" onclick="deleteQna()" value="삭제하기" class="submit_btn">
					</form>
					<form method="post" action="modifyQnA.do">
						<input type="hidden" value="${qnadto.qa_no}" name="no">
						<input type="submit" value="수정하기" class="submit_btn">
					</form>
				</c:if>
				<c:if test="${idSession=='admin'}">
					<form method="post" action="replyQnA.do" >
						<input type="hidden" value="${qnadto.qa_no}" name="no">
						<input type="hidden" value="${qnadto.qa_groupNum}" name="groupNum">
						<input type="submit" value="답글달기" class="submit_btn">
					</form>
				</c:if>
			</td>
		</tr>
	</table>
</div>
<%--footer --%>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>