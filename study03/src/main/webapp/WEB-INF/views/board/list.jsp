<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시글 목록</title>
<%@ include file = "/WEB-INF/views/include/memberHeader.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			location.href = "${path}/board/write.do";
		});
	});
	
	function list(page) {
		location.href = "${path}/board/list.do?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
	}
	
</script>
</head>
<body>
<%@ include file = "/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>게시글 목록</h2>
	
	<form name="form1" method="post" action="${path }/board/list.do">
		<select name="searchOption">
			<!-- 검색조건을 검색처리후 결과화면에 보여주기위해 c:out 출력태크 사용, 삼향연산자 -->
			<option value="all" <c:out value="${map.searchOption == 'all'?'selected':'' }"></c:out>>제목+이름+내용</option>
			<option value="userName" <c:out value="${map.searchOption == 'userName'?'selected':'' }"></c:out>>이름</option>
			<option value="content" <c:out value="${map.searchOption == 'content'?'selected':'' }"></c:out>>내용</option>
			<option value="title" <c:out value="${map.searchOption == 'title'?'selected':'' }"></c:out>>제목</option>
		</select>
		<input name="keyword" value="${map.keyword }"/>
		<input type="button" name="submit" value="조회"/>
		
		<!-- 로그인한 사용자만 글쓰기 버튼 활성화 -->
		<c:if test="${sessionScope.userId != null }">
			<button type="button" id="btnWrite">글쓰기</button>
		</c:if>
	</form>
	
	<!-- 레코드의 갯수를 출력 -->
	${map.count }개의 개시물이 있습니다.
	
	<table border="1" style="width:600px;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${map.list }" var="row">
			<c:choose>
				<c:when test="${row.show == 'y' }">
					<tr>
						<td>${row.bno }</td>
						<!-- 게시글 상세보기 페이지로 이동시 게시글 목록페이지에 있는 검색조건, 키워드 현재페이지 값을 유지하기 위해 -->
						<td>
							<a href="${path }/board/view.do?bno=${row.bno}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">${row.title }
								<!-- 댓글이 있으면 게시글 이름 옆에 출력하기 -->
								<c:if test="${row.recnt > 0 }">
									<span style="color:red;">(${row.recnt })</span>
								</c:if>
							</a>
						</td>
						<td>${row.userName }</td>
						<td><fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd"/></td>
						<td>${row.viewcnt }</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<!-- show 컬럼이 n일때 (삭제된 상태일 때)-->
					<tr>
						<td colspan="5" align="left">
							<c:if test="${row.recnt > 0 }">
								<a href="${path }/board/view.do?bno=${row.bno}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keword=${map.keyword}">
									삭제된 게시물 입니다.
									<!-- 댓글이 있으면 게시글 이름 옆에 출력하기 -->
									<span style="color:red;">(${row.recnt })</span>
								</a>
							</c:if>
							<c:if test="${row.recnt == 0 }">삭제된 게시물 입니다.</c:if>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<tr>
			<td colspan="5">
				<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면 [처음]하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curBlock > 1 }">
					<a href = "javascript:list('1')">[처음]</a>
				</c:if>
				
				<!-- 이전페이지 블록으로 이동 : 현재 페이지 블록이 1보다 크면 [이전] 하이퍼링크 출력 -->
				<c:if test="${map.boardPager.curBlock > 1 }">
					<a href = "javascript:list('${map.boardPager.prevPage }')">[이전]</a>
				</c:if>
				
				<!-- 하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
				<c:forEach begin="${map.boardPager.blockBegin }" end="${map.boardPager.blockEnd }" var="num">
					<c:choose>
						<c:when test="${num == map.boardPager.curPage }">
							<span style="color:red">${num }</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:list('${num }')">${num }</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음] 하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curPage <= map.boardPager.totBlock }">
					<a href="javascript:list('${map.boardPager.nextPage }')">[다음]</a>
				</c:if>
				
				<!-- 끝페이지로 이동 : 현재 페이지가 전페 페이지보다 작거나 같으면 [끝] 하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curPage <= map.boardPager.totPage }">
					<a href="javascript:list('${map.boardPager.totPage }')">[끝]</a>
				</c:if>
			</td>
		</tr>
		
	</table>

</body>
</html>