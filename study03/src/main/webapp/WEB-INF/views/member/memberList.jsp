<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원목록</title>
<%@ include file="/WEB-INF/views/include/memberHeader.jsp" %>
</head>
<script type="text/javascript">
	
</script>
<body>
<%@ include file="/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>회원 목록</h2>
	<input type="button" value="회원등록" onclick="location.href='${path}/member/write.do'"/>
	
	<table border="1" style="width:700px;">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>회원가입일자</th>
		</tr>
	
		<c:forEach items="${list }" var="list">
			<tr>
				<td>${list.userId }</td>
				<td><a href="${path }/member/view.do?userId=${list.userId}">${list.userName }</a></td>
				<td>${list.userEmail }</td>
				<td><fmt:formatDate value="${list.userRegdate }" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>