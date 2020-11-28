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
<link rel="stylesheet" href="css\modifyBoard.css">
<script>
	function modifyReview(){
		var sel = modifyForm.thema.value;
		if(sel=="**테마선택**"){
			alert("리뷰남길 테마를 선택해주세요");
		}
		else{
			if(confirm("수정하시겠습니까?")==true){
				modifyForm.submit();
			 }else{
			     return false;
			 }
		}
		
	}
</script>
<title>탈출후기</title>
</head>
<body>
<%--상단 메뉴바--%>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>탈출후기</p>
</div>
<div id="contents">
<h2>후기 수정</h2>
<form name="modifyForm" method="post" action="modifyReviewOk.do">
	<table id="writeTb">
		
		<tr>
			<td>
				<select name="thema" id="th_Select">
					<option>**테마선택**</option>
					<c:if test="${idSession=='admin'}">
						<option ${revdto.rev_thema eq '**공지글**' ? 'selected' : ''}>**공지글**</option>
					</c:if>
					<c:forEach var="th" items="${themaList}">
						<option ${revdto.rev_thema eq th.them_name? 'selected' : ''}>${th.them_name}</option>
					</c:forEach>
				</select>
				<input type="text" name="title" required value="${revdto.rev_title}" id="titletd">
			</td>
		</tr>
		<tr>
			<td><textarea name="contents" cols="100" rows="15" id="textbx">${revdto.rev_contents}</textarea></td>
		</tr>
		<tr>
			<td id="btntd"><input type="button" onclick="modifyReview()" value="수정하기" id="submit_btn"></td>
		</tr>
	</table>
	<input type="hidden" name="no" value="${revdto.rev_no}">
</form>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>