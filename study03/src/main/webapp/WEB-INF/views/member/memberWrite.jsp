<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원등록 페이지</title>
<%@ include file="/WEB-INF/views/include/memberHeader.jsp" %>
</head>
<script type="text/javascript">
	
</script>
<body>
<%@ include file="/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>회원 등록</h2>
	<h2>${path }</h2>
	
	<form name="form1" method="post" action="${path }/member/insert.do">
		<table>
			<tr>
				<td>아이디</td>
				<td><input name="userId"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input name="userPw"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="userName"/></td>
			</tr>
			<tr>
				<td>이메일 주소</td>
				<td><input name="userEmail"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>