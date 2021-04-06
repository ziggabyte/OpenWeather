<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.io.IOException"  %> 
 <%@ page import="model.WeatherBean" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ziggi's Weather App</title>
<link href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css" rel="stylesheet">
<script src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<link rel="stylesheet" href="styles.css">
<script defer src="index.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Roboto&family=Ultra&display=swap" rel="stylesheet"></head>
<body>
<%!
private void makePrevSearchDiv(JspWriter out, String city, String country, String id) throws IOException {
			out.print("<div class='prevSearch'>" + city + " " + country + "</div>");
}

%>
<%
Cookie[] cookies = request.getCookies();
WeatherBean weatherBean = (WeatherBean) request.getAttribute("weatherBean");

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

if (weatherBean != null) {
	String weatherReport = weatherBean.getLocalTime() + " "
			+ weatherBean.getCity() + " has " 
			+ weatherBean.getClouds() + " and a temperature of " 
			+ weatherBean.getTemperature() + " degrees C.";

	out.print("<div class='weatherContainer'><h2>Weather report</h2>");
	out.print("<div class='weather'>");
	out.print(weatherReport);
	out.print("</div></div>");
}

%>
<header>
<h1>Ziggi's weather app</h1>
</header>
<div class="formContainer">
<h2>Search</h2>
<form action="<%=request.getContextPath()%>/OWServlet" method="post">

<label class="mdc-text-field mdc-text-field--filled mdc-text-field--no-label">
  <span class="mdc-text-field__ripple"></span>
  <input class="mdc-text-field__input" type="text" placeholder="City" aria-label="Label" name="city" required>
  <span class="mdc-line-ripple"></span>
</label>

<label class="mdc-text-field mdc-text-field--filled mdc-text-field--no-label">
  <span class="mdc-text-field__ripple"></span>
  <input class="mdc-text-field__input" type="text" placeholder="Country code" aria-label="Label" name="country" required>
  <span class="mdc-line-ripple"></span>
</label>

<button type="submit" class="mdc-button mdc-button--raised">
  <span class="mdc-button__label">Give me the weather!</span>
</button>
</form>
</div>

</body>
</html>