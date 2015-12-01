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
            <a class="quizifyIcon" href="index.jsp">Quizify</a>
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
    </div>
</body>
</html>