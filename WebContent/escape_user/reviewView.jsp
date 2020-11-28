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
<link rel="stylesheet" href="css\reviewView.css?o">
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\header.css?o">
<title>탈출후기</title>
<script>
 	function deleteRev(){
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
<p>탈출후기</p>
</div>
<div id="contents">
<h2>후기 게시물</h2>
	<table id="viewTb">
		<tr>
			<td id="titletd"><b>[${revdto.rev_thema}]</b>${revdto.rev_title}</td>
		</tr>
		<tr>
			<td id="idtd">${revdto.rev_id} ${revdto.rev_wtime}</td>
		</tr>
		<tr>
			<td colspan="2" id="contentstd" >${revdto.rev_contents}</td>
		</tr>
		<tr>
			<td id="btnTd">
				<c:if test="${idSession==revdto.rev_id ||idSession=='admin'}">
					<form name="deleteForm" method="post" action="deleteReview.do">
						<input type="hidden" value="${revdto.rev_no}" name="no"> 
						<input type="button" onclick="deleteRev()" value="삭제하기" class="submit_btn">
					</form>
					<form method="post" action="modifyReview.do">
						<input type="hidden" value="${revdto.rev_no}" name="no">
						<input type="submit" value="수정하기" class="submit_btn">
					</form>
				</c:if>
			</td>
		</tr>
	</table>
	<div>
		<span id="rply">댓글 달기</span>
		<form method="post" action="writeComt.do">
			<textarea name="contents" id="rplyTxt"></textarea>
			<input type="hidden" name="id" value="${idSession}">
			<input type="hidden" name="rNo" value="${revdto.rev_no}">
			<c:choose>
				<c:when test="${idSession!=null}">
					<input type="submit" value="입력" id="rplyBtn">
				</c:when>
				<c:otherwise>
					<p>댓글을 쓰려면 로그인이 필요합니다.</p>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
	<table id="rplyTb">
		<c:forEach var="c" items="${comtList}">
			<tr>
				<td id="rplyid">${c.comt_id}</td>
				<td>
					${c.comt_contents}
					<c:if test="${idSession eq c.comt_id}">
						<a href="deleteComt.do?no=${c.comt_no}&rNo=${revdto.rev_no}">삭제</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%--footer --%>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>