<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify | Quizzes</title>
</head>
<body>
	<div class="pageContainer">

		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>
	
		<h3>Select a Quiz below!</h3>
	
		<c:forEach var="quizOption" items="${allQuizzes }">
			<p><a href="GetQuiz.do?quizID=${quizOption.id }">${quizOption.name } (Average Score: ${quizOption.averageScore })</a></p>
		</c:forEach>
	
	</div>
</body>
</html>