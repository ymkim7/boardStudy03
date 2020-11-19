<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>댓글</title>
<%@ include file = "/WEB-INF/views/include/memberHeader.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		//댓글 수정
		$("#btnReplyUpdate").click(function(){
			
			var detailReplytext = $("#detailReplytext").val();
			
			$.ajax({
				url : "${path}/reply/update/${vo.rno}"
				, type : "put"
				, headers : {
					"Content-Type" : "application/json"
				}
				, data : JSON.stringify({
					replytext : detailReplytext
				})
				, dataType : "text"
				, success : function(result) {
					if(result == "success") {
						$("#modifyReply").css("visibility", "hidden");
						listReplyRest("1");
					}
				}
			});
		});
		
		//댓글 상세화면 닫기
		$("#btnReplyClose").click(function(){
			$("#modifyReply").css("visibility", "hidden");
		});
		
		//댓글 삭제
		$("#btnReplyDelete").click(function(){
			if(confirm("삭제하시겠습니까?")) {
				$.ajax({
					url : "{path}/reply/delete/${vo.rno}"
					, type : "delete"
					, success : function(result) {
						if(result == "success") {
							alert("삭제되었습니다.");
							$("#modifyReply").css("visibility", "hidden");
							listReplyRest("1");
						}
					}
				});
			}
		});
		
	});
	
</script>
</head>
<body>
	댓글 번호 : ${vo.rno }<br>
	<textarea rows="5" cols="82" id="detailReplytext">${vo.replytext }</textarea>
	<div style="text-align:center;">
		<!-- 본인 댓글만 수정, 삭제가 가능하도록 처리 -->
		<c:if test="${sessionScope.userId == vo.replyer }">
			<button type="button" id="btnReplyUpdate">수정</button>
			<button type="button" id="btnReplyDelete">삭제</button>
		</c:if>
			<button type="button" id="btnReplyClose">닫기</button>
	</div>
</body>
</html>