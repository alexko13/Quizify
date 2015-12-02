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
	
		<form action="CreateNewQuiz.do" method="GET">
			<input type="submit" value="Make a Quiz" />
		</form>
		
		<form action="DisplayCreatedQuizzes.do" method="GET">
			<input type="submit" value="My Quizzes" />
		</form>
		
	</div>
</body>
</html>