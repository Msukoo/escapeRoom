<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Minsu">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\footer.css?o">
<link rel="stylesheet" href="css\header.css?o">
<link rel="stylesheet" href="css\mypage.css?w">
<title>마이 페이지</title>
<script>
	function pwCheck(){
		 var pw = joinForm.pw.value;
		 var pw2 = joinForm.pw2.value;
		 
		 if(pw==""||pw!=pw2){
			alert("비밀번호를 확인해 주세요");
		 }else if(pw.length<8||pw.length>12){
			alert("8자리 이상, 12자리 이하의 비밀번호를 입력해주세요");
		 }else if(pw==pw2){
			 joinForm.submit();
		 }
	}
</script>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>마이페이지</p>
</div>
<div class="contents" id="user">
	<table>
		<tr id="info">
			<td><span id="name">${userdto.user_name}</span> 님</td>
			<td><img src="image\trophy_.png" id="trophy"> <span id="trophyNum">${userdto.user_trophy}</span> 개</td>
		</tr>
	</table>
</div>
<div class="contents" id="infomenu">
	<table>
		<tr id="infotr">
			<td><a href="mypage_booking.do" class="other">내 예약현황</a></td>
			<td><a href="mypage.do" id="current">내 정보</a></td>
			<td><a href="mypage_review.do" class="other">내가 쓴 리뷰</a></td>
		</tr>
	</table>
</div>
<div id="contents">
<form name="joinForm" method="post" action="modifyUserInfo.do">
	<h2>내 정보 보기</h2><br>
	<table>
		<tr>
			<td class="subject"><b>아이디</b></td>
			<td class="subject_">${userdto.user_id}</td>
		</tr>
		<tr>
			<td class="subject"><b>이름</b></td>
			<td class="subject_">${userdto.user_name}</td>
		</tr>
		<tr>
			<td class="subject"><b>비밀번호</b></td>
			<td class="subject_"><input type="password" name="pw" class="input"></td>
		</tr>
		<tr>
			<td class="subject"><b>비밀번호 확인</b></td>
			<td class="subject_"><input type="password" name="pw2" class="input"></td>
		</tr>
		<tr>
			<td class="subject"><b>이메일</b></td>
			<td class="subject_"><input type="text" name="email" class="input" value="${userdto.user_email}"></td>
		</tr>
		<tr>
			<td class="subject"><b>연락처</b></td>
			<td class="subject_">
				<c:set var="phone" value="${userdto.user_phone}"/>
				<input type="text" name="phone1" class="pinput" required value="${fn:substring(phone,0,3)}"> <b>-</b>
				<input type="text" name="phone2" class="pinput" required value="${fn:substring(phone,3,7)}"> <b>-</b>
				<input type="text" name="phone3" class="pinput" required value="${fn:substring(phone,7,12)}">
			</td>
		</tr>
		<tr>
			<td class="subject"><b>선호하는 유형</b></td>
			<td class="subject_">
				<input type="radio" name="prefer" value="lock" id="lock" ${(userdto.user_prefer eq "lock")? "checked" : "" }>
					<label for="lock"><span></span>자물쇠 </label>
				<input type="radio" name="prefer" value="device" id="device" ${(userdto.user_prefer eq "device")? "checked" : "" }>
					<label for="device"><span></span>장치 </label>
				<input type="radio" name="prefer" value="both" id="both" ${(userdto.user_prefer eq "both")? "checked" : "" }>
					<label for="both"><span></span>모두 선호함 </label>
			</td>
		</tr>
		<tr id="btntr">
			<td colspan="2"><input type="button" value="정보수정 완료" onclick="pwCheck()" id="joinBtn"></td>
		</tr>
	</table>
</form>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>