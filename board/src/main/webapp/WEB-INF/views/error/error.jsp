<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ERROR</title>
</head>
<body>
<h1>ERROR 발생</h1>
<h3>ERROR 내용: ${comment}</h3>
<h4>${exception.getMessage()}</h4>
<ul>
	<c:forEach items="${exception.getStackTrace()}" var="stack">
		<li>${stack.toString()}</li>
	</c:forEach>
</ul>
</body>
</html>