<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>댓글</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	
	<table style="width:700px;">
		<c:forEach items="${list }" var="row">
			<tr>
				<td>
					${row.userName }(<fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd"/>)<br>
					${row.replytext }<br>
				
					<!-- 본인 댓글만 수정버튼 생성되도록 처리 -->
					<c:if test="${sessionScope.userId == row.replyer }">
						<input type="button" id="btnModify" value="수정" onclick="showReplyModify('${row.rno}')"/>
						<%-- <button type="button" id="btnModify" onclick="showReplyModify('${row.rno}')">수정</button> --%>
					</c:if>
					<hr>
				</td>
			</tr>
		</c:forEach>
		
		<!-- 페이징 처리 -->
		<tr style="text-align:center;">
			<td>
				<!-- 현재 페이지 블럭이 1보다 크면 처음으로 이동 -->
				<c:if test="${replyPager.curBlock > 1 }">
					<a href="javascript:listReply('1')">[처음]</a>
				</c:if>
				
				<!-- 현재 페이지 블럭이 1보다 크면 이전 페이지 블럭으로 이동 -->
				<c:if test="${replyPager.curBlock > 1 }">
					<a href="javascript:listReply('${replyPager.prevPage }')">[이전]</a>
				</c:if>
				
				<!-- 페이지 블럭 처음부터 마지막 블럭까지 1씩 증가하는 페이지 출력 -->
				<c:forEach begin="${replyPager.blockBegin }" end="${replyPager.blockEnd }" var="num">
					<c:choose>
						<c:when test="${num == replyPager.curPage }">${num }&nbsp;</c:when>
						<c:otherwise>
							<a href="javascript:listReply('${num }')">${num }</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 다음페이지로 이동 -->
				<c:if test="${replyPager.curBlock <= replyPager.totBlock }">
					<a href="javascript:listReply('${replyPager.nextPage }')">[다음]</a>
				</c:if>
				
				<!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 끝으로 이동 -->
				<c:if test="${replyPager.curBlock <= replyPager.totBlock }">
					<a href="javascript:listReply('${replyPager.totPage }')">[끝]</a>
				</c:if>
			</td>
		</tr>
		
	</table>
	
	<!-- 댓글 수정 영역 -->
	<div id="modifyReply"></div>

</body>
</html>