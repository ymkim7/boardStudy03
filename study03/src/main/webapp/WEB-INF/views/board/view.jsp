<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시글 작성</title>
<%@ include file = "/WEB-INF/views/include/memberHeader.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		//댓글 목록 불러오기
		//listReply("1");
		
		//json 리턴 방식
		//listReply2();
		
		//rest 방식
		listReplyRest("1");
		
		//댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
		$("#btnReply").click(function(){
			
			//폼데이터로 입력
			//reply();
			
			//json으로 입력
			replyJson();
			
		});
		
		//목록 버튼 클릭 이벤트 : 버튼 클릭시 상세보기화면에서 있던 페이지, 검색옵션, 키워드 값응ㄹ 가지고 목록으로 이동
		
		$("#btnList").click(function(){
			location.href = "${path}/board/list.do?curPage=${curPage}&searchOption=${serachOption}&keyword=${keyword}";
		});
		
		$("#btnDelete").click(function(){
			if(confirm("삭제 하시겠습니까?")) {
				document.form1.action = "${path}/board/delete.do";
				document.form1.submit();
			}
		});
		
		$("#btnUpdate").click(function(){
			//var title = document.form1.title.value; ==> name속성으로 처리할 경우
			//var content = document.form1.content.value;
			//var writer = document.form1.writer.value;
			
			var title = $("#title").val();
			var content = $("#content").val();
			/* var writer = $("#writer").val(); */
			
			if(title == "") {
				alert("제목을 입력해주세요.");
				document.form1.title.focus();
				return;
			}
			
			if(content == "") {
				alert("내용을 입력해주세요.");
				document.form1.content.focus();
				return;
			}
			
			/* if(writer == "") {
				alert("이름을 입력해주세요.");
				document.form1.writer.focus();
				return;
			} */
			
			document.form1.action = "${path}/board/update.do";
			document.form1.submit();
			
		});
		
		//게시글 삭제
		$("btnDelete").click(function(){
			
			//댓글이 존재하는 게시물의 삭제처리 방지
			var count = "${count}";
			
			//댓글의 수가 0보다 크면 팝업, 함수 종료
			
			if(count > 0) {
				alert("댓글이 있는 게시물은 삭제하실 수 없습니다.");
				return;
			}
			
			//댓글의 수가 0이면 삭제처리
			if(confirm("삭제하시겠습니까?")) {
				document.form1.action = "${path}/board/delete.do";
				document.form1.submit();
			}
		});
		
	});
	
	//댓글 쓰기 (json방식)
	function replyJson() {
		
		var replytext = $("#replytext").val();
		var bno = "${dto.bno}";
		
		//비밀댓글 체크여부
		var secretReply = "n";
		
		//태그.is (":속성") 체크여부 true/false
		if($("#secretReply").is(":checked")) {
			secretReply = "y";
		}
		
		$.ajax({
			url : "${path}/reply/insertRest.do"
			, type : "post"
			, headers : {
				"Content-Type" : "application/json"
			}
			, dataType : "text"
			, data : JSON.stringify({
				bno : bno
				, replytext : replytext
				, secretReply : secretReply
			})
			, success : function(){
				alert("댓글이 등록되었습니다.");
				//listReply2();
				//listReply("1");
				listReplyRest("1");
			}
		});
	}
	
	//댓글 쓰기 (폼데이터 방식)
	function reply() {
		
		var replytext = $("#replytext").val();
		var bno = "${dto.bno}";
		
		//비밀댓글 체크여부
		var secretReply = "n";
		
		//태그.is (":속성") 체크여부 true/false
		if($("#secretReply").is(":checked")) {
			secretReply = "y";
		}
		
		//alert(secretReply);
		//비밀댓글 파라미터 추가
		
		var param = "replytext=" + replytext + "&bno=" + bno + "&secretReply=" + secretReply;
		
		$.ajax({
			url : "${path}/reply/insert.do"
			, type : "post"
			, data : param
			, success : function(){
				alert("댓글이 등록되었습니다.");
				//listReply2();
				listReply("1");
			}
		});
	}
	
	//Controller 방식
	//댓글 목록 1
	function listReply(num) {
		$.ajax({
			url : "${path}/reply/list.do?bno=${dto.bno}&curPage=" + num
			, type : "get"
			, success : function(result){
				//responseText가 result에 저장됨
				$("#listReply").html(result);
			}
		});
	}
	
	//RestController 방식 (Json)
	//댓글 목록2 (json)
	function listReply2() {
		$.ajax({
			url : "${path}/reply/listJson.do?bno=${dto.bno}"
			, type : "get"
			, success : function(result) {
				var output = "<table>";
				
				for(var i in result) {
					output += "<tr>";
					output += "<td>" + result[i].userName;
					output += "("+changeDate(result[i].regdate)+")<br>";
					output += result[i].replytext + "</td>";
					output += "<tr>";
				}
				output += "</table>";
				$("#listReply").html(output);
			}
		});
	}
	
	//날짜 변환 함수 작성
	function changeDate(date) {
		date = new Date(parseInt(date));
		year = date.getFullYear();
		month = date.getMonth();
		day = date.getDate();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		strDate = year + "-" + month + "-" +day + " " + hour + ":" + minute + ":" + second;
		return strDate;
	}
	
	function listReplyRest(num) {
		$.ajax({
			url : "${path}/reply/list/${dto.bno}/" + num
			, type : "get"
			, success : function(result) {
				$("#listReply").html(result);
			}
		});
	}
	
	function showReplyModify(rno) {
		$.ajax({
			url : "${path}/reply/detail/" + rno
			, type : "get"
			, success : function(result) {
				$("#modifyReply").html(result);
				$("#modifyReply").css("visibility", "visible");
			}
		});
	}
	
</script>

<style type="text/css">
	#modifyReply {
		width : 600px;
		heigth : 130px;
		background-color : gray;
		padding : 10px;
		z-index : 10;
		visibility : hidden;
	}
</style>

</head>
<body>
<%@ include file = "/WEB-INF/views/include/memberMenu.jsp" %>

	<h2>게시글 보기</h2>
	
	<c:choose>
		<c:when test="${dto.show == 'y' }">
			<form name="form1" method="post">
				<div>작성일자 : <fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd"/></div>
				<div>조회수 : ${dto.viewcnt }</div>
				<div>제목<input name="title" id="title" size="80" value="${dto.title }" placeholder="제목을 입력해주세요."/></div>
				<div>내용<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${dto.content }</textarea></div>
				<div>이름<input name="writer" id="writer" value="${dto.writer }" placeholder="이름을 입력해주세요."/>${dto.userName }</div>
			
				<div style="width:650px; text-align:center;">
					<!-- 게시물번호를 히든 처리 -->
					<input type="hidden" name="bno" value="${dto.bno }"/>
					
					<!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
					<c:if test="${sessionScope.userId == dto.writer }">
						<button type="button" id="btnUpdate">수정</button>
						<button type="button" id="btnDelete">삭제</button>
					</c:if>
					
					<!-- 상세보기 화면에서 게시글 목록화면으로 이동 -->
					<button type="button" id="btnList">목록</button><hr>
				</div>
			</form>
			
			<div style="width:650px; text-align:center;"><br>
				<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
				<c:if test="${sessionScope.userId != null }">
					<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 입력하세요."></textarea><br>
					<!-- 비밀댓글 체크박스 -->
					<input type="checkbox" id="secretReply"/>비밀 댓글
					<button type="button" id="btnReply">댓글 작성</button>
				</c:if>
			</div>
		</c:when>
		<c:otherwise>
			삭제된 게시글 입니다.
		</c:otherwise>
	</c:choose>
	
	<!-- 댓글목록 출력할 위치 -->
	<div id="listReply"></div>
	
</body>
</html>