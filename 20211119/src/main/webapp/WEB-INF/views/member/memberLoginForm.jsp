<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
   function inputCheck() { // 메소드 생성
      if(frm.id.value == "") {
         alert("아이디를 입력하세요. ");
         frm.id.focus();
         return false;
      } else if(frm.password.value == "") {
         alert("비밀번호를 입력하세요. ");
         frm.password.focus();
         return false;
      }
         frm.submit(); // 정상적으로 입력했을 경우 
   }
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>

	<div align="center">
		<div>

		</div>
		<div><p><p><p></div>
		<div>
			<h2>로 그 인</h2>
		</div>
		<div>
		
			<form id="frm" action="memberLogin.do" method="post"> <!--  frm.submit 했을 때  -->
				<div>

					<table border="1">
						<tr>
							<th width="150">아 이 디</th>
							<td width="200"><input type="text" id="id" name="id">
							</td>

						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="200"><input type="password" id="password" name="password"></td>
						</tr>
					</table>
				</div><br>
				<input type ="button" onclick="inputCheck()" value="로그인"> &nbsp;&nbsp;&nbsp; <!--  여백 띄우기 -->
				<input type ="reset" value="취 소">&nbsp;&nbsp;&nbsp;
			
			</form>
		</div><br>
	
	</div>

</body>
</html>