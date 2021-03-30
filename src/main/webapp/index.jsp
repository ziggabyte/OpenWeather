<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ziggi's Weather App</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/OWServlet" method="post">

<label for="city">City: </label>
<input type="text" name="city">

<label for="country">Country code: </label>
<input type="text" name="country">

<input type="submit" value="Give me the weather!">
</form>

</body>
</html>