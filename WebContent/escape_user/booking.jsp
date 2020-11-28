<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="css\booking.css?v">
<title>예약하기</title>
<script>
	function sortCheck(){
		var thema = sortForm.thema.value;
		var date = sortForm.date.value;
		
		if(thema=="테마선택"||date=="날짜선택"){
			alert("원하시는 테마와 날짜를 선택해주세요")
		}else{
			sortForm.submit();
		}
	}
	
	function bookingOk() {
		 if(confirm("예약하시겠습니까?")==true){
			 bookingForm.submit();
		 }else{
		     return false;
		 }
	}
</script>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div id="title">
<p>예약하기</p>
</div>

<%--예약 선택박스 --%>
<div id="contents">
<h2>예약하기</h2>
<form name="sortForm" method="post" action="sortTime.do">
<select name="date" class="choice">
	<option>날짜선택</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" /></c:set>
	<option ${(select2 eq date)? "selected" : ""}>
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*1000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*2000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*3000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*4000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*5000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*6000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
	<c:set var="date"><fmt:formatDate value="<%=new Date(new Date().getTime() + (long)60*60*24*7000)%>" type="DATE" pattern="yyyy-MM-dd"/></c:set>
	<option ${(select2 eq date)? "selected" : ""}>	
		${date}
	</option>
</select>

<select name="thema" class="choice" id="choice2">
	<option>테마선택</option>
	<c:forEach var="thema" items="${themaList}">
		<option ${(select1 eq thema.them_name)? "selected" : ""}>${thema.them_name}</option>
	</c:forEach>
</select>
<input type="button" onclick="sortCheck()" value="찾기" id="choiceBtn">
</form>
<%--타임 테이블--%>
<c:if test="${bookingList==null}">
<P id="nochoice">원하는 날짜와 테마를 선택해주세요</P>
</c:if>
<c:if test="${bookingList!=null}">
		<table id="bookingtb">  
			<c:forEach var="i" begin="11" end="19" step="2">
				<tr>
					<td class="list">${select2}</td><%--선택날짜 --%>
					<td class="list">${select1}</td><%--선택테마 --%>
					<td class="list">${i}:00</td>
					<td>
						<c:forEach var="thema" items="${themaList}">
							<c:if test="${select1 eq thema.them_name}">
								<c:set var="code" value="${thema.them_code}"/>
								<c:forEach var="j" begin="1" end="${thema.them_level}" step="1">
									<img src="image\star.png" class="star">
								</c:forEach>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:set var="flag" value="0"/> <%--flag 0 초기화 --%>
						<c:forEach var="book" items="${bookingList}"> 
								<%--동일한 예약있으면 flag==1 --%>
								<c:if test="${(book.book_date eq select2) && (book.book_thema eq select1) && (book.book_time eq i)}">
									<c:set var="flag" value="1"/>
								</c:if>
						</c:forEach>
						<c:choose> <%--0:예약하기  1:예약완료 --%>
							<c:when test="${flag eq '0'}">
								<form name="bookingForm" method="post" action="bookingOk.do">
										<input type="hidden" name="code" value="${code}">
										<input type="hidden" name="thema" value="${select1}">
										<input type="hidden" name="date" value="${select2}">
										<input type="hidden" name="time" value="${i}">
										<input type="submit" value="예약하기"  class="bookBtn" onClick="bookingOk()">
								</form>
							</c:when>
							<c:otherwise>
								<span id="soldout">예약완료</span>
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>