<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- core 가장 많이 사용함  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><h1>${message}</h1></div> <!-- el 표현식 -->
	<div>
		<c:if test="${author == 'ADMIN' }" >
			<a href="memberList.do">멤버 목록보기</a>
			</c:if>
	<!-- <c:if test="${not empty id}"> -->	 <!-- id가 not empty면 -->
		
	<!-- <h3>${id },${name },${author }</h3> -->	<!-- session에 담긴 이메일, 관리자, ADMIN 출력-->
			
		<!-- </c:if> <!-- 여기까지 실행 -->
	</div>
</div>
</body>
</html>