<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1>
		<a href="HomePage.jsp">Quizify</a>
	</h1>
	<p>Logged in as ${account.username }</p>
	
	<form action="DisplayQuizzes.do" method="GET">
		<input type="submit" value="Take a quiz!" />
	</form>
	
</body>
</html>