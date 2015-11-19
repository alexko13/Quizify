<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACCOUNT TEST</title>
</head>
<body>

	<h1>
		<a href="index.html">Qu¿z¿fy</a>
	</h1>

	<p>${account }</p>
	<p>${account.submissions[0] }</p>
	<p>${account.submissions[0].submissionTime }</p>
	<p>${account.submissions[0].responses[0] }</p>

</body>
</html>