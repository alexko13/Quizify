<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify | Results</title>
</head>
<body>
	<div class="pageContainer">
	
		<h1>
			<a class="quizifyIcon" href="HomePage.jsp">Quizify</a>
		</h1>
		
		<h2>${submission.quiz.name }</h2>
	
		<p>You answered: ${submission.numberCorrect } / ${submission.numberOfQuestions } (${submission.percentCorrect }%) correctly!</p>
			
		<c:forEach var="response" items="${submission.responses}">
			<h3>
				<c:out value="${response.question.text }" />
			</h3>
			
			<h4>Your Answer: ${response.answer.text }</h4>
			<c:forEach var="answer" items="${response.question.answers}">
				<p>
					<c:out value="${answer.text }" />
					<c:if test="${answer.isCorrect eq 'Y'.charAt(0)}">
						(Correct)
					</c:if>
				</p>
			</c:forEach>
		</c:forEach>
	</div>
</body>
</html>