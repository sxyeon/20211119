<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- core 가장 많이 사용함  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function authorEdit(str, num) {
		var id = str;
		var author = $('#author'+num).val();
		alert(id + "===" + author);
		
		// ajax로 처리하는 부분
		// 권한값을 어떻게 찾을지 고민
		$.ajax({
			url: 'ajaxAuthorUpdate.do', // 호출명
			type: 'post', // 전송방식 get, post
			data: {id: id, author: author}, // 전달할 데이터 K:V(변수명:값) 
			dataType: 'text', // 처리된 결과 받을 타입(html, text, json, xml 등)
			success: function(data) { // data에 리턴값이 담겨있음
				if(data == 'yes') {
					alert(id + "님의 권한이 변경되었습니다.");
				} else {
					alert(id + "님의 권한변경을 실패했습니다.")
				}
			}
		});
	}
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><h1> ‍ 멤 버 목 록  </h1></div>
	<div>
		<table border="1">
			<tr>
				<th width="150"> 아이디 </th>
				<th width="150"> 이 름 </th>
				<th width="150"> 전화번호 </th>
				<th width="400"> 주 소 </th>
				<th width="100"> 권 한 </th>
				<th width="100"> 권한수정 </th>
			</tr>
			<c:forEach items="${members }" var="member" varStatus="status"> <!-- varStatus는 상태변수 -->
				<tr onmouseover="this.style.background='#CCEEFF';"
					onmouseleave="this.style.background='#CCEEFF'">
					<td align="center">${member.id }</td> <!-- vo의 속성name과 같아야함 -->
					<td align="center">${member.name }</td>
					<td align="center">${member.tel }</td>
					<td>&nbsp;${member.address }</td>
					<td align="center">
						<select name="author" id="author${status.count}" >
							<option value="ADMIN" <c:if test="${member.author eq 'USER' }">selected</c:if>>ADMIN</option>
							<option value="USER" <c:if test="${member.author eq 'ADMIN' }">selected</c:if>>USER</option>
						</select>
					</td>
					<td align="center">
						<button type="button" onclick="authorEdit('${member.id }')">변경</button>
					</td>
				</tr>
				</c:forEach>
		</table>
		<div>
			<button type="button" onclick="location.href='home.do'">홈으로</button>
		</div>
	</div>
	<!-- <c:forEach items="${members}" var="member"> --> <!-- members라는 아이템(객체)을 member라는 변수로 읽겠음 -->
	<!-- ${member.id } : ${member.password } : ${member.name }  --> <!-- id : vo 객체가 가지고 있는 이름 그대로 써야함 --> <br>
	<!-- </c:forEach> -->
	
</div>
</body>
</html>