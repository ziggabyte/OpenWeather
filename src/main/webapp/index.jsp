<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.io.IOException"  %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ziggi's Weather App</title>
<link rel="stylesheet" href="styles.css">
<script defer src="index.js"></script>
</head>
<body>
<%!
private void makePrevSearchDiv(JspWriter out, String city, String country, String id) throws IOException {
			out.print("<div class='prevSearch'>" + city + " " + country + "</div>");
}

%>
<%
Cookie[] cookies = request.getCookies();

if (cookies.length > 1) {
	String[] splitIntoPairs = cookies[1].getValue().split("-");
	out.print("<div class='prevSearchContainer'><h2>Previous searches</h2>");
	
	int id = 0;
	for (String s : splitIntoPairs) {
		String[] temp = s.split(":");
		makePrevSearchDiv(out, temp[0], temp[1], String.valueOf(id));
		id++;
	}
	out.print("</div>");
}

%>
<header>
<h1>Ziggi's weather app</h1>
</header>

<main>
<form action="<%=request.getContextPath()%>/OWServlet" method="post">
<label for="city">City: </label>
<input type="text" name="city">
<label for="country">Country code: </label>
<input type="text" name="country">
<input type="submit" value="Give me the weather!">
</form>
</main>

</body>
</html>