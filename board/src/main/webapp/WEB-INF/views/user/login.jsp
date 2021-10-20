<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div class="col-lg-3" style="float: none; margin: auto">
		<h1 style="color: #116688; font-family: Malgun Gothic; font-weight: bold;" onclick="location.href='/'">로그인</h1>
		<br>
		<form action="/user/loginPost" method="post">
			<label for="userId">아이디</label> 
			<input type="text" id="userId" name="userId" class="form-control" style="width: 100%;" placeholder="아이디를 입력해주세요." required /> 
			<br> 
			<label for="userPw">비밀번호</label> 
			<input type="password" id="userPw" name="userPw" class="form-control" style="width: 100%;" placeholder="비밀번호를 입력해주세요." required /> 
			<br>
			<div style="text-align: center">
			<button type="submit" style="margin: auto" class="btn btn-primary">로그인</button>
			<button type="button" style="margin: auto" class="btn btn-secondary" onclick="location='/'">메인</button>
			<a href="/user/register">회원가입</a>
			</div>
		</form>
	</div>
</body>
</html>