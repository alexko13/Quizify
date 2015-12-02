<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css"/>
<title>Quizify | Sign Up</title>
</head>
<body>
	<div class="pageContainer">
	
		<h1>
			<a class="quizifyIcon" href="index.jsp">Quizify</a>
		</h1>
		
		<p>${signUpError }</p>
		<h4>Sign Up:</h4>
		<form:form action="SignUp.do" method="POST" commandName="newAccount">
			<form:input type="text" placeholder="email" path="email" />
			<form:errors path="email" /> <br/>
			<form:input type="text" placeholder="username (5-30)" path="username" />
			<form:errors path="username" /> <br />
			<form:input type="password" placeholder="password (5-30)" path="password" />
			<form:errors path="password" /> <br />
			<input type="submit" value="Sign Up" />
		</form:form>
	</div>
</body>
</html>