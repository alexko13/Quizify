<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quizify | Results</title>
</head>
<body>

	<h1>
		<a href="HomePage.jsp">Quizify</a>
	</h1>
	
	<h2>${quiz.name }</h2>

		<p>You answered: ${score.correct } / ${score.total } (${score.percentCorrect }%) correctly!</p>
		
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

</body>
</html>