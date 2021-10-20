<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="/resources/favicon/favicon.ico" type="image/x-icon">
<link rel="icon" href="/resources/favicon/favicon.ico" type="image/x-icon">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>게시판</title>
<style>
body {
	background: #fff;
}

a {
	text-decoration: none;
}

.button {
	width: 100px;
	background-color: #116688;
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

.searchBut {
	width: 50px;
	background-color: #116688;
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 13px;
	cursor: pointer;
	height: 33px;
	width: 60px;
}

.table>thead {
	background-color: #116688;
	color: white;
}

.hit {
	animation-name: blink;
	animation-duration: 1.5s;
	animation-timing-function: ease;
	animation-iteration-count: infinite;
}

@keyframes blink {
	from {color: white;}
	30% {color:yellow;}
	to {color: red; font-weight: bold;}
}
</style>
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
		<h1 style="color: #116688; font-family: Malgun Gothic; font-weight: bold;" onclick="location.href='/'">게시판</h1>
		<!-- 검색 기능  -->
		
		<form name="search" method="get" action="/">
			<div style="text-align: right; vertical-align: middle;">
				<button type="submit" class="searchBut" style="float: right">검색</button>
				<input name="keyword" type="search" placeholder="검색어를 입력하세요" value="${keyword}" class="form-control" style="width: 25%; float: right; margin-left: 5px; margin-right: 5px;">
				<select name="searchOption" class="form-control" style="width: 13%; float: right">
					<option value="all" <c:out value="${searchOption == 'all'?'selected':''}"/>>전체</option>
					<option value="BOARD_SUBJECT" <c:out value="${searchOption == 'BOARD_SUBJECT'?'selected':''}"/>>제목</option>
					<option value="BOARD_CONTENT" <c:out value="${searchOption == 'BOARD_CONTENT'?'selected':''}"/>>내용</option>
					<option value="BOARD_WRITER" <c:out value="${searchOption == 'BOARD_WRITER'?'selected':''}"/>>작성자</option>
				</select>
				<br><br>
			</div>
			<h5 style="color: #168; float: right">${total}개의게시물이있습니다.</h5>
			<br>
		</form>
		<!-- 목록 테이블  -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="text-align: center">번호</th>
					<th width=400px>제목</th>
					<th style="text-align: center">조회수</th>
					<th style="text-align: center">작성자</th>
					<th>작성 시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList}" var="board">
					<c:if test="${board.viewCnt >= 10}">
						<tr>
							<th scope="row" style="text-align: center; color: red">인기</th>
							<td>
								<a href="/view?sequence=${board.sequence}&nowPage=${paging.nowPage}&searchOption=${searchOption}&keyword=${keyword}">${board.title}</a>
								<c:if test="${board.replyCnt > 0 }">
									<span style="color: grey">&nbsp;(${board.replyCnt})</span>
								</c:if>
								<c:if test="${board.viewCnt >= 10}">
									<span class="hit">&nbsp;&nbsp; hot!</span>
								</c:if>
							</td>
							<td style="text-align: center">${board.viewCnt}</td>
							<td>${board.writer}</td>
							<td>
								<fmt:parseDate value="${board.writeTime}" pattern="yyyy-MM-dd HH:mm:ss" var="date" />
								<fmt:formatDate value="${date}" type="DATE" pattern="yyyy-MM-dd HH:mm" />
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:forEach items="${boardList}" var="board">
					<c:set var="i" value="${i+1}" />
					<tr>
						<th scope="row" style="text-align: center">${i+start}</th>
						<td>
							<a href="/view?sequence=${board.sequence}&nowPage=${paging.nowPage}&searchOption=${searchOption}&keyword=${keyword}">${board.title}</a>
							<c:if test="${board.replyCnt > 0 }">
								<span style="color: grey">&nbsp;(${board.replyCnt})</span>
							</c:if>
							<c:if test="${board.viewCnt >= 10}">
								<span class="hit">&nbsp;&nbsp; hot!</span>
							</c:if>
						</td>
						<td style="text-align: center">${board.viewCnt}</td>
						<td>${board.writer}</td>
						<td>
							<fmt:parseDate value="${board.writeTime}" pattern="yyyy-MM-dd HH:mm:ss" var="date" />
							<fmt:formatDate value="${date}" type="DATE" pattern="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 처리  -->
		<div style="text-align: center">
			<c:if test="${paging.nowPage > 1}">
				<a href="/?nowPage=1&searchOption=${searchOption}&keyword=${keyword}">&lt;&lt;</a>
			</c:if>
			<c:if test="${paging.blockStart !=1}">
				<a href="/?nowPage=${paging.blockStart-1}&searchOption=${searchOption}&keyword=${keyword}">&lt;</a>
			</c:if>
			<c:forEach var="num" begin="${paging.blockStart}" end="${paging.blockEnd}">
				<c:choose>
					<c:when test="${num == paging.nowPage}">
						<span style="color: red">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="/?nowPage=${num}&searchOption=${searchOption}&keyword=${keyword}">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.blockEnd != paging.totalPage}">
				<a href="/?nowPage=${paging.blockEnd+1}&searchOption=${searchOption}&keyword=${keyword}">&gt;</a>
			</c:if>
			<c:if test="${paging.nowPage < paging.totalPage}">
				<a href="/?nowPage=${paging.totalPage}&searchOption=${searchOption}&keyword=${keyword}">&gt;&gt;</a>
			</c:if>
		</div>
		<!-- 글쓰기 버튼  -->
		<button onclick="location='write'" class="button" style="float: right">글쓰기</button>
	</div>
</body>
</html>
