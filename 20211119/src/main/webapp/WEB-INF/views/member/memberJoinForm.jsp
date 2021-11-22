<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function CheckEmail(str) { <!-- email check정규식 -->
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(!reg_email.test(str)) {
			return false;
		} else {
			return true;
		}
	}

	function idCheck() { <!-- 아이디 중복체크 -->
		var id = $("#id").val();
		if(!CheckEmail(id)) { <!-- 입력된 아이디가 이메일 형식인지 체크 -->
			alert("eMail을 입력하세요.");
			$("#id").val("");
			$("#id").focus();
			return;
		}
		
		$.ajax({
			url : "ajaxIdCheck.do",
			type : "post",
			data : {chkid : id},
			dataType : "text",
			success : function(data) {
				if(data == '0') { // 0과 같으면 사용할 수 있고 1이면 존재하는 아이디
					alert(id + "사용할 수 있는 아이디입니다.");
					$("#idchk").val("Yes");
					$("#idchk").attr("disabled", true); // 중복체크 버튼 비활성화
					$("#password").focus();
				} else {
					alert(id + " 이미 존재하는 아이디입니다.");
					$("#id").val("");
					$("#id").focus();
				}
			}
		});
		
	}
	
	function formCheck() {
		if($("#idchk").val() != 'Yes') {
			alert("아이디 중복체크를 해주세요.");
			return false;
		}
		
		if($("#password").val() != $("#passwordChk").val()) {
			alert("패스워드가 일치하지 않습니다.");
			$("#password").val("");
			$("#passwordChk").val("");
			$("#password").focus();
			return false;
		}
		
		$("#frm").submit();
	}
</script>
</head>

<body>
<jsp:include page="../home/header.jsp" />
   <div align ="center">
   <div><h3>회원가입</h3></div>
   <form id="frm" action="memberJoin.do" method="POST">
   <div>
      <table border="1">
         <tr>
            <th width="150">* 아 이 디</th>
            <td width="350"><input type="email" id="id" name="id" required="required" placeholder="id를 입력하세요">
            &nbsp;&nbsp;<button type="button" id="idchk" onclick="idCheck()" value="No">중복체크</button></td>
         </tr>
         <tr>
            <th>* 비밀번호</th>
            <td><input type="password" id="password" name="password" required="required" placeholder="비밀번호 입력"></td>
         </tr>
         <tr>
            <th>* 비밀번호 확인</th>
            <td><input type="password" id="passwordChk" name="passwordChk" required="required" placeholder="비밀번호 입력"></td>
         </tr>
         <tr>
            <th>* 이름</th>
            <td><input type="text" id="name" name="name" required="required" placeholder="이름을 입력하세요"></td>
         </tr>
         <tr>
            <th>전화번호</th>
            <td><input type="text" id="tel" name="tel"></td>
         </tr>
         <tr>
            <th>주소</th>
            <td><input type="text" id="address" name="address" size="68"></td>
         </tr>
      </table>
   </div><br>
      <input type="button" onclick="formCheck()" value="회원가입">&nbsp;&nbsp;&nbsp;
      <input type="reset" value="취소">
   </form>   
</div>

</body>
</html>