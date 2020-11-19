<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>홈페이지에 오신걸 환영합니다.</title>
</head>
<script type="text/javascript">
	
</script>
<body>
<%@ include file="/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>홈페이지 연습</h2>

	<c:if test="${msg == 'success' }">
		<h2>${sessionScope.userName }(${sessionCcope.userId })님 환영합니다.</h2>
	</c:if>
	
</body>
</html>
