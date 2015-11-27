<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quizify</title>
</head>
<body>

	<h1>
		<a href="index.jsp">Quizify</a>
	</h1>

	<p>${signInError }</p>

	<h4>Sign In:</h4>
	<form action="SignIn.do" method="POST">
		<input type="text" placeholder="username" name="username" /> <br />
		<input type="text" placeholder="password" name="password" /> <br />
		<input type="submit" value="Sign In" />
	</form>
	
	<br />
	
	<p>No account? No problem!</p>
	<form action="GoToSignUp.do" method="POST">
		<input type="submit" value="Sign Up" />
	</form>

</body>
</html>