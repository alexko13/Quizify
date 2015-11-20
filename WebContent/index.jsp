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


	<h3>Sign In:</h3>
	<form action="SignIn.do" method="POST">
		<input type="text" placeholder="username" name="username" />
		<input type="text" placeholder="password" name="password" />
		<input type="submit" value="Sign In" />
	</form>
	<p><font color="red">${signInError }</font></p>
	<br/>
	<p>No account?</p>
	<p>TODO: Create Sign Up</p>

</body>
</html>