<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원정보 상세 페이지</title>
<%@ include file="/WEB-INF/views/include/memberHeader.jsp" %>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		var formObj = $("form[name='form1']");
		
		$("#btnUpdate").click(function(){
			if(confirm("수정하시겠습니까?")) {
				formObj.attr("action", "${path}/member/update.do");
				formObj.attr("method", "post");
				formObj.submit();
			}
		});
		
		$("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니까?")) {
				formObj.attr("action", "${path}/member/delete.do");
				formObj.attr("method", "post");
				formObj.submit();
			}
		});
		
	});
</script>
<body>
<%@ include file="/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>회원정보 상세 페이지</h2>
	<h2>${path }</h2>
	
	<form name="form1" method="post">
		<table border="1" style="width:400px;">
			<tr>
				<td>아이디</td>
				<!-- 아이디는 수정이 불가능하도록 리드온리 -->
				<td><input name="userId" value="${dto.userId }" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="userName" value="${dto.userName }"/></td>
			</tr>
			<tr>
				<td>이메일주소</td>
				<td><input name="userEmail" value="${dto.userEmail }"/></td>
			</tr>
			<tr>
				<td>회원가입일자</td>
				<td><fmt:formatDate value="${dto.userRegdate }" pattern="yyyy-MM-dd"/> </td>
			</tr>
			<tr>
				<td>회원정보 수정일자</td>
				<td><fmt:formatDate value="${dto.userUpdatedate }" pattern="yyyy-MM-dd"/> </td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="btnUpdate" name="btnUpdate" value="수정"/>
					<input type="button" id="btnDelete" name="btnDelete" value="삭제"/>
					<div style="color:red;">${message }</div>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>