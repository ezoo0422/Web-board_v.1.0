<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>회원 가입</title>
<script type="text/javascript">

	//비밀번호 확인
	function passCheck() {
		var password = document.getElementById('userPw');
		var passwordCheck = document.getElementById('userPwCheck');
		var confirmMsg = document.getElementById('confirmMsg');
		var correctColor = "blue";
		var wrongColor = "red";
		
		if(password.value != '' && passwordCheck.value != '') {
			if(password.value == passwordCheck.value) {
				confirmMsg.style.color = correctColor;
				confirmMsg.innerHTML = "비밀번호 일치";
			} else {
				confirmMsg.style.color = wrongColor;
				confirmMsg.innerHTML = "비밀번호 불일치"
			}
		}
	}
	
	// 아이디 중복확인 처리
	function checkId() {
		var userId = $("#userId").val();
		var checkId = 0;
		console.log(userId);
		$.ajax({
			url: "register/checkId",
			type: "POST",
			dataType: 'JSON',
			contentType : "application/json; charset=UTF-8",
			data: userId,
			
			success: function(data) {
				if(data == 0) {
					console.log("아이디 없음");
					document.userInfo.idDuplication.value="idCheck";
					alert("사용할 수 있는 아이디 입니다.");
					
				} else {
					console.log("아이디 있음");
					alert("중복된 아이디가 존재합니다.");
				}
			}
		})
	}
	
	// 아이디 중복확인 여부 (입력값 변경 시 재 확인)
	function inputCheckId() {
		document.userInfo.idDuplication.value="idUncheck";
	}
	
	// 제출 전 아이디 중복확인, 비밀번호 일치 확인
	function checkInfo() {
		var form = document.userInfo;
		
		if (form.idDuplication.value != "idCheck") {
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		
		if (form.userPw.value != form.userPwCheck.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="col-lg-3" style="float: none; margin: auto">
		<form action="/user/register" method="post" name="userInfo" onsubmit="return checkInfo()">
			<h1 style="color: #116688; font-family: Malgun Gothic; font-weight: bold;" onclick="location.href='/'">회원가입</h1>
			<br>
			<label for="userId">아이디</label> 
			<button id="duplicate_check" type="button" onclick="checkId();" class="btn btn-primary" style="float: right; height: 33px; margin:3px">중복체크</button>
			<input type="text" name="userId" id="userId" onkeydown="inputCheckId()" class="form-control" style="width: 100%;" placeholder="아이디를 입력해주세요." required /> <br> 
			<input type="hidden" name="idDuplication" value="idUncheck"/> 
			<label for="userName">이름</label> 
			<input type="text" name="userName" class="form-control" style="width: 100%;" placeholder="이름을 입력해주세요." required /> <br> 
			<label for="userEmail">이메일</label> 
			<input type="email" name="userEmail" class="form-control" style="width: 100%;" placeholder="이메일을 입력해주세요." required /> <br> 
			<label for="userPw">비밀번호</label> 
			<input type="password" name="userPw" id="userPw" class="form-control" onchange="passCheck()" style="width: 100%;" placeholder="비밀번호를 입력해주세요." required /> <br> 
			<label for="userPwCheck">비밀번호 확인</label> 
			<input type="password" name="userPwCheck" id="userPwCheck" class="form-control" onchange="passCheck()" style="width: 100%;" placeholder="비밀번호를 입력해주세요." required /> <br>
			<span id="confirmMsg"></span>
			<div style="text-align: center">
				<button type="submit" style="margin: auto" class="btn btn-primary">가입</button>
				<button type="button" style="margin: auto" class="btn btn-secondary" onclick="location='/'">메인</button>
			</div>
		</form>
	</div>
</body>
</html>