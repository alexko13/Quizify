<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Quizify | My Quizzes</title>
</head>
<body>
	<div class="pageContainer">

		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>

		<h3>Created Quizzes</h3>
		<br />

		<c:forEach var="quizOption" items="${accountQuizzes }">
			<form action="DeleteQuiz.do?quizID=${quizOption.id }" method="POST">
				<p>${quizOption.name }</p>
				<input type="submit" value="Delete" />
			</form>
			<br />
		</c:forEach>

	</div>
</body>
</html>