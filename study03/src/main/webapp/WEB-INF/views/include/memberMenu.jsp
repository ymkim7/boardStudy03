<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title></title>
</head>
<script type="text/javascript">
	
</script>
<body>
	<div>
		<a href="${path }/board/list.do">게시판</a>
	</div>
	<div>
		<a href="${path }/member/list.do">회원가입</a>
	</div>
	<c:choose>
		<c:when test="${sessionScope.userId == null }">
			<a href="${path }/member/login.do">로그인</a>
		</c:when>
		<c:otherwise>
			${sessionScope.userName }님이 로그인중입니다.
			<a href="${path }/member/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</body>
</html>