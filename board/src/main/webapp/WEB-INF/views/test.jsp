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
					comments = "��� �� ����� �����ϴ�.";
				} else {
					$(result).each(function() {
						comments += '�ۼ���: ' + replyWriter;
						comments += '��� ����: ' + replyText;
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
		<h3>��� �ۼ�</h3>
		<table>
			<tr>
				<td>��� �ۼ���</td>
				<td>
					<input type="text" name="replyText" required />
				</td>
			</tr>
			<tr>
				<td>��� ����</td>
				<td>
					<input type="text" name="replyWriter" required />
				</td>
			</tr>
		</table>
		<button type="button">��� ����</button>
	</div>
	<div id="replies"></div>
	
</body>
</html>