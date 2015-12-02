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
		
		<form action="CreateNewQuiz.do" method="POST">
			<select name="numberOfQuestions" >
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
			</select>
			<input type="submit" value="Update Number of Questions" />
		</form>
		
		
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