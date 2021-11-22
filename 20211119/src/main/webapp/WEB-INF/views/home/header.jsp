<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<body>
<div align="center">
   <div>
      <ul>
         
         <li><a class="active" href="home.do"> 홈 </a></li>
         <c:if test="${empty id }"> <!-- id가 비어있으면 로그인 -->
            <li><a href="memberLoginForm.do"> 로그인 </a></li>
         </c:if>
         <c:if test="${not empty id }"> <!-- id가 있으면 로그아웃 -->
            <li><a href="logout.do"> 로그아웃 </a></li>
         </c:if>
         <c:if test="${author eq 'ADMIN' }">
            <li><a href="memberList.do"> 멤버목록 </a></li>
         </c:if>
         <li><a href="noticeList.do"> 공지사항 </a></li>
         <li><a href="#"> 회사소개 </a></li>
         <c:if test="${not empty id }"> <!--  로그인했으면 제품 -->
            <li><a href="#"> 제품소개 </a></li>
            <li><a href="memberInfo.do"> 나의정보 </a></li>
         </c:if>
         <c:if test="${empty id }"> <!-- id가 없다면 회원가입 -->
            <li><a href="memberJoinForm.do"> 회원가입</a></li>
         </c:if>
      </ul>
   </div>
</div>
</body>
</html>