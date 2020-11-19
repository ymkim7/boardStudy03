<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	//컨텍스트 메서드 호출
	String path = request.getContextPath();
%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>컨트롤러 호출 연습페이지</title>
</head>
<script type="text/javascript">

	//json 형식
	//{변수명:값, 변수명:값}
	//{naem:"냉장고", price:90000}
	function doF() {
		$.ajax({
			url : "<%=path%>/sample/doF"
			, type : "post"
			, success : function(result) {
				console.log("result ", result);
				$("#result").html("상품명 : " + result.productName + ", 가격 : " + result.productPrice);
			}
		});
	}
	
</script>
<body>
	<h2>컨트롤러 호출 연습 페이지</h2>
		<a href="<%=path%>/sample/doA">doA</a> : model<br/>
		<a href="<%=path%>/sample/doB">doB</a> : 단순호출<br/>
		<a href="<%=path%>/sample/doC">doC</a> : modelAndView<br/>
		<a href="<%=path%>/sample/doD">doD</a> : redirect<br/>
		
		<!-- 1. doF() 함수 호출 -->
		<a href="javascript:doF()">doF</a> : json<br/>
		
		<!-- 2. 함수 호출 결과물 출력 -->
		<div id="result"></div>
</body>
</html>
