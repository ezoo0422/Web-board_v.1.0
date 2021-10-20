<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 수정</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<style>
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
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : '내용을 입력해주세요.',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR',
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	  	});
	});
</script>
</head>
<body>
	<div class="col-lg-6" style="float: none; margin: auto">
		<form name="write" method="post" action="/update">
			<br>
			<table style="width: 100%">
				<caption style="font-size: 30px; color: #168; font-weight: bold">게시글 수정</caption>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td>제목</td>
					<td style="width: 60%">
						<input type="text" name="title" class="form-control" value="${view.title}" style="width: 100%;" placeholder="제목을 입력해주세요." required />
					</td>
					<td>&nbsp;&nbsp; 작성자</td>
					<td>
						<input type="text" name="writer" class="form-control" value="${view.writer}" style="width: 100%;" placeholder="작성자를 입력해주세요." required />
					</td>
				</tr>
			</table>
			<br>
			<textarea id="summernote" name="content" required>${view.content}</textarea>
			<br><br><input type="hidden" name="sequence" value="${view.sequence}">
			<button type="submit" class="button" style="float: right;" onclick="if(!confirm('게시글을 수정하시겠습니까?')){return false;}">등록</button>
			<button type="button" style="float: right;" onclick="history.back();" class="button">취소</button>
		</form>
	</div>
</body>
</html>