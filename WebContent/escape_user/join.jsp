<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Minsu">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css\join.css">
<script>
	function pwCheck(){
		 var pw = joinForm.pw.value;
		 var pw2 = joinForm.pw2.value;
		 
		 if(pw==""||pw!=pw2){
			alert("비밀번호를 확인해 주세요");
		 }else if(pw.length<8||pw.length>12){
			alert("8자리 이상, 12자리 이하의 비밀번호를 입력해주세요");
		 }else if(pw==pw2){
			 if(confirm("가입하시겠습니까?")==true){
				 joinForm.submit();
			 }else{
			     return false;
			 }
		 }
	}
	
	function duplication_Check(){
		window.open("/ESCAPE_ROOM/escape_user/duplicationCheck.jsp", "duplicationCheck", "height=250px, width=300px");
	}
</script>
<title>join</title>
</head>
<body>
<header>
<div id="top_navi"> 
	<a href="index.jsp"><img src="image\logo.JPG" alt="logo" id="logo"></a>
		<ul id="menu">
			<li class="menuli"><a href="index.jsp" class="menu">Home</a></li>
			<li class="menuli"><a href="thema.do" class="menu">테마</a></li>
			<li class="menuli"><a href="booking.do" class="menu">예약하기</a></li>
			<li id="drop" class="menuli"><a href="#" class="menu">커뮤니티</a>
				<div class="sub_menu">
					<a href="#" class="comu_menu">탈출후기</a>
					<a href="#" class="comu_menu">QnA</a>
				</div>
			</li>
		</ul>
			<a href="login.jsp" class="login/out">로그인/회원가입</a>
</div>
</header>
<form name="joinForm" method="post" action="joinOk.do">
<div id="join">
	<h1>sign up</h1>
	<table>
		<tr>
			<td class="subject"><b>아이디</b></td>
			<td><input type="text" name="id" class="input" readOnly onfocus="duplication_Check()">
				<input type="button" value="중복체크" onclick="duplication_Check()" id="idCheck" />
			</td>
		</tr>
		<tr>
			<td class="subject"><b>이름</b></td>
			<td><input type="text" name="name" class="input" required></td>
		</tr>
		<tr>
			<td class="subject"><b>이메일</b></td>
			<td><input type="email" name="email" class="input" required>
			</td>
		</tr>
		<tr>
			<td class="subject"><b>비밀번호</b></td>
			<td><input type="password" name="pw" class="input"></td>
		</tr>
		<tr>
			<td class="subject"><b>비밀번호 확인</b></td>
			<td><input type="password" name="pw2" class="input"></td>
		</tr>
		<tr>
			<td class="subject"><b>연락처</b></td>
			<td>
				<input type="text" name="phone1" class="pinput" required> <b>-</b>
				<input type="text" name="phone2" class="pinput" required> <b>-</b>
				<input type="text" name="phone3" class="pinput" required>
			</td>
		</tr>
		<tr>
			<td class="subject"><b>선호하는 유형</b></td>
			<td><input type="radio" name="prefer" value="lock" id="lock" checked><label for="lock"><span></span>자물쇠 </label>
				<input type="radio" name="prefer" value="device" id="device"><label for="device"><span></span>장치 </label>
				<input type="radio" name="prefer" value="both" id="both"><label for="both"><span></span>모두 선호함 </label>
			</td>
		</tr>
		<tr id="btntr">
			<td colspan="2"><input type="button" value="가입하기" onclick="pwCheck()" id="joinBtn"></td>
		</tr>
	</table>
</div>
</form>
</body>
</html>