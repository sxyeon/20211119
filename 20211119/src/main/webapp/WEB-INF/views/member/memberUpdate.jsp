<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script>
	$(function() {
			$("#btn1").click(function() {
				frm.action = "memberEditSave.do";
				frm.submit();
			});
			
			$("#btn2").click(function() {
				alert("패스워드가 일치하지 않습니다.")
				return false;
			});
	});
</script>
</head>
<body>
<jsp:include page="../home/header.jsp" />
<div align="center">
	<div><h1>나의 정보 수정</h1></div>
	<div>
		<form id="frm" method="post">
		<div>
			<table border="1">
				<tr>
					<th width="150">아이디</th>
					<td width="150">${member.id }</td>
					<th width="150">이 름</th>
					<td width="150"><input type="text" id="name" name="name" value="${member.name }"></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" id="password" name="password" value="${member.password }"></td>
					<th>패스워드확인</th>
					<td><input type="password" id="passwordChk" name="passwordChk" value="${member.password }"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" id="tel" name="tel" value="${member.tel }"></td>
					<th>권 한</th>
					<td>${member.author }</td>
				</tr>
				<tr>
					<th>주 소</th>
					<td colspan="3">
						<input type="text" id="address" name="address" size="68" value="${member.address }"></td>
				</tr>
			</table>
		</div><br>
		<div>
			<button type="button" id="btn1">저장하기</button> &nbsp;&nbsp;&nbsp;
			<button type="button" id="btn2">수정취소</button>
		</div>
		<div>
			<input type="hidden" id="id" name="id" value="${member.id }">
			<input type="hidden" id="author" name="author" value="${member.author }">
		</div>
		</form>
	</div>
</div>
</body>
</html>