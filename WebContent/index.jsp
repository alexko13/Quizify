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

	<h4>Sign In:</h4>
	<form action="SignIn.do" method="POST">
		<input type="text" placeholder="username" name="username" /> <br /> 
		<input type="text" placeholder="password" name="password" /> <br />
		<input type="submit" value="Sign In" />
	</form>
	<p>
		<font color="red">${signInError }</font>
	</p>
	<br />
	<p>No account? No problem!</p>
	<h4>Sign Up:</h4>
	<form action="SignUp.do" method="POST">
		<input type="text" placeholder="email" name="email" /> <br /> 
		<input type="text" placeholder="username" name="username" /> <br />
		<input type="text" placeholder="password" name="password" /> <br />
		<input type="submit" value="Sign Up" />
	</form>
	<p>
		<font color="red">${signUpError }</font>
	</p>

</body>
</html>