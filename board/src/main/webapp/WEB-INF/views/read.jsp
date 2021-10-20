<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>게시글</title>
<style>
body {
	background: #fff;
}
table {
	width: 600px;
}
.button {
	width: 100px;
	background-color: #168;
	border: none;
	color: white;
	padding: 10px 0;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 13px;
	margin: 4px;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(document).ready(function () {
		getReplyList();
	});
	
	// 댓글 목록 출력
	function getReplyList() {
		var sequence = ${view.sequence};
		var replyUrl = "/replies/all/" + sequence;
		$.ajax({
			url: replyUrl,
			type: 'GET',
			dataType: 'json',
			success: function(result) {
				var comments = "";
				if(result.length < 1) {
					comments = "등록 된 댓글이 없습니다.";
				} else {
					$(result).each(function() {
						comments += '<div id="reply' + this.replyNo + '">';
                        comments += '<table>';
                        comments += '<tr><th width=15%>작성자</th><td width=40%>'+this.replyWriter+'</td><th>작성 일자</th><td>'+this.regDate+'</td></tr>'
                        /* comments += '<tr></tr>' */
                        comments += '<tr><th>댓글 내용</th><td colspan="3">'+this.replyText+'</td></tr>'
                        comments += '</table>'
                        comments += '<button type="button" id="replyDeleteBtn" data-replyNo=' + this.replyNo + ' onclick="deleteBtn('+this.replyNo + ')" class="btn btn-secondary" style="float: right">삭제</button>';
                        comments += '<button type="button" id="replyUpdateBtn" data-replyNo=' + this.replyNo + ' onclick="updateView('+this.replyNo+',\'' + this.replyText +'\',\'' + this.replyWriter+'\')" class="btn btn-primary" style="float: right">수정</button>';
                        comments += '</div>';
                        comments += '<br/><hr>';
					});
				};

					$("#replyList").html(comments);
			}
		});
	}
	
	// 댓글 작성 처리
	function writeBtn() {
			var replyText = $('#newReplyText').val();
			var replyWriter = $('#newReplyWriter').val();
			var boardSeq = ${view.sequence};
			
			$.ajax({
				type: "POST",
				url: "/replies",
				headers: {
					"Content-type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType: 'text',
				data: JSON.stringify({
					boardSeq: boardSeq,
					replyText: replyText,
					replyWriter: replyWriter
				}),
				success: function(result) {
					alert("댓글이 등록되었습니다.");
					getReplyList();
					$('#newReplyWriter').val('');
					$('#newReplyText').val('');
					document.body.scrollIntoView(false);
				}
			})	

	}
	
	// 댓글 수정 화면
	function updateView(replyNo, replyText, replyWriter) {
		var comment = "";
		comment += '<div id="reply' + this.replyNo + '">';
		comment += '<h4 style="color: #168; font-family: Malgun Gothic; font-weight: bold;">댓글 수정</h4>';
		comment += '<label for="replyUpdateWriter">작성자</label>';
        comment += '<input type="text" id="replyUpdateWriter" name="replyUpdateWriter"class="form-control" style="width: 100%;" value="' + replyWriter +'"/>';
        comment += '<br/>';
        comment += '<label for="replyUpdateText">댓글 내용</label>';
        comment += '<textarea id="replyUpdateText" name="replyUpdateText" class="form-control" style="width: 100%;">' + replyText + '</textarea>';
        comment += '<br/>';
		comment += '<button type="button" id="replyDeleteBtn" onclick="getReplyList()" class="btn btn-secondary" style="float: right">취소</button>';
		comment += '<button type="button" id="replyUpdateBtn" onclick="updateBtn(' + replyNo + ')" class="btn btn-primary" style="float: right">작성</button>';
		comment += '</div>'; 
		comment += '<br/><hr>';
		
		$('#reply' + replyNo).replaceWith(comment);
		$('#reply' + replyNo + '#replyText').focus();
	}
	
	// 댓글 수정 처리
	function updateBtn(replyNo) {
		console.log("수정 버튼");
		var replyUrl = "/replies/" + replyNo;
		var replyWriter = $('#replyUpdateWriter').val();
		var replyText = $('#replyUpdateText').val();
		var boardSeq = ${view.sequence};
		var jsonData = ''
		
		console.log(replyUrl);
		console.log("수정 버튼" + replyWriter + replyText);
		$.ajax({
			url: replyUrl,
			type: 'PUT',
			dataType: 'text',
			/* contentType: "application/json ; charset=utf-8" */
			headers: {"Content-Type": "application/json"
					,"X-HTTP-Method-Override" : "PUT"},
			data: JSON.stringify({
				replyText: replyText,
				replyWriter : replyWriter
			}),
			success: function(result) {
				console.log("수정 성공");
				alert("댓글이 수정되었습니다.");
				getReplyList();
			}
		})
	}
	
	// 댓글 삭제 처리
	function deleteBtn(replyNo) {
		var replyUrl = "/replies/" + replyNo;
		
		$.ajax({
			type:'delete',
			url: replyUrl,
			headers: {"Content-Type": "application/json"
				,"X-HTTP-Method-Override" : "DELETE"},
			dataType: "text",
			success: function(result) {
				console.log("댓글 삭제 성공");
				alert("댓글이 삭제되었습니다.");
				getReplyList();
			}
		})
	}

</script>
</head>
<body>
	<div class="col-lg-6" style="float: none; margin: auto">
		<div style="text-align: right">
			<br>
			<c:if test="${not empty login}">
			<p>${login.userName}님 안녕하세요.
			<a href="/user/logout">로그아웃</a></p>
			</c:if>
			<c:if test="${empty login}">
				<a href="/user/login">로그인</a>
				<a href="/user/register">&nbsp;&nbsp;회원가입</a>
			</c:if>
		</div>
		<h1 style="color: #168; font-family: Malgun Gothic; font-weight: bold;" onclick="location.href='/'">게시글</h1>
		<br> <br>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 20%">제목</th>
					<td>${view.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${view.writer}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${view.viewCnt}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${view.content}</td>
				</tr>
			</tbody>
		</table>
		<br> <br> <br>
		<div style="text-align: center">
			<button type="button" onclick="location.href='/?nowPage=${nowPage}&searchOption=${searchOption}&keyword=${keyword}'" class="button">메인</button>
			<button type="button" onclick="location.href='/updateView?sequence=${view.sequence}'" class="button">수정</button>
			<a href='/delete?sequence=${view.sequence}' class="button" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">삭제</a>
		</div>
		<hr>		
		<h3 style="color: #168; font-family: Malgun Gothic; font-weight: bold;">댓글</h3>
		<br>
		<div>
			<h4 style="color: #168; font-family: Malgun Gothic; font-weight: bold;">댓글 작성</h4>
			<label for="newReplyWriter">작성자</label>
			<input type="text" id="newReplyWriter" name="newReplyWriter"class="form-control" style="width: 100%;" placeholder="댓글 작성자를 입력해주세요." required/>
			<br>
			<label for="newReplyText">댓글 내용</label>
			<textarea id="newReplyText" name="newReplyText" class="form-control" style="width: 100%;" placeholder="댓글 내용을 입력해주세요." required></textarea>
			<br>
			<button type="button" id="replyWriteBtn" onclick="writeBtn()" class="btn btn-primary" style="float: right">댓글 저장</button>
		<br><br>
		</div>
		<hr>
		<br>
		<div id="replyList"></div>
	</div>
</body>
</html>