<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify | Home</title>
</head>
<body>
	<div class="pageContainer">
		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>
		<p>Logged in as ${account.username }</p>
	
		<form action="SignOut.do" method="POST">
			<input type="submit" value="Sign Out" />
		</form>
	
		<form action="DisplayQuizzes.do" method="GET">
			<input type="submit" value="Take a Quiz" />
		</form>
	
		<form action="CreateNewQuiz.do?numberOfQuestions=4" method="POST">
			<input type="submit" value="Make a Quiz" />
		</form>
		
		<form action="DisplayCreatedQuizzes.do" method="GET">
			<input type="submit" value="My Quizzes" />
		</form>
		
		<p>History</p>
		<c:forEach var="sub" items="${account.submissions }">
			<p>${sub.submissionTime } ${sub.quiz.name} ${sub.percentCorrect }%</p>
		</c:forEach>
		
	</div>
</body>
</html>