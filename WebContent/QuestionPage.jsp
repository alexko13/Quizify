<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify | Quiz</title>
</head>
<body>
	<div class="pageContainer">
		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>
		
		<h2>${quiz.name }</h2>
		<form action="GetResult.do" method="POST">
			<c:forEach var="question" items="${quiz.questions}">
				<h3>
					<c:out value="${question.text }" />
				</h3>
				<c:forEach var="answer" items="${question.answers}">
					<p>
						<input type="radio" name="${question.id }" value="${answer.id }" />
						<c:out value="${answer.text }" />
					</p>
				</c:forEach>
			</c:forEach>
			<input type=submit value="Submit Answers" />
		</form>
	</div>
</body>
</html>