<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		getReplyList();
	});
	function getReplyList() {
		var replyNo = $('#replyNo').val();
		var replyUrl = "/replies/" + replyNo;
		
		$.ajax({
			url: replyUrl,
			type: 'POST',
			dataType: 'json',
			success: function(result) {
				var comments = "";
				if(result.length < 1) {
					comments = "등록 된 댓글이 없습니다.";
				} else {
					$(result).each(function() {
						comments += '작성자: ' + replyWriter;
						comments += '댓글 내용: ' + replyText;
					});
				};
				
					$("#replyList").heml(comments);
			}
		})
		
	}
</script>
</head>
<body>
	<div>
		<h3>댓글 작성</h3>
		<table>
			<tr>
				<td>댓글 작성자</td>
				<td>
					<input type="text" name="replyText" required />
				</td>
			</tr>
			<tr>
				<td>댓글 내용</td>
				<td>
					<input type="text" name="replyWriter" required />
				</td>
			</tr>
		</table>
		<button type="button">댓글 저장</button>
	</div>
	<div id="replies"></div>
	
</body>
</html>