<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify</title>
</head>
<body>
	<div class="pageContainer">
		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>
		<form:form action="SaveNewQuiz.do" commandName="newQuiz">
			<form:input size="50" path="name" placeholder="Quiz Name" /> <br/> <hr/>
			<c:forEach var="question" items="${newQuiz.questions}" varStatus="questionStatus">
				<form:input size="50" path="questions[${questionStatus.index }].text" placeholder="Question ${questionStatus.index + 1} text" />
				<br />
				<c:forEach var="answer" items="${question.answers}" varStatus="answerStatus">
					<form:input size="50" path="questions[${questionStatus.index }].answers[${answerStatus.index }].text" placeholder="Answer ${answerStatus.index + 1} text" />
					Correct: <form:radiobutton path="questions[${questionStatus.index }].answers[${answerStatus.index }].isCorrect" value="Y"/>
					Incorrect: <form:radiobutton path="questions[${questionStatus.index }].answers[${answerStatus.index }].isCorrect" value="N"/>
					<br/>
				</c:forEach>
				<hr/>
			</c:forEach>
			<input type=submit value="Save my quiz!" />
		</form:form>
	</div>
</body>
</html>