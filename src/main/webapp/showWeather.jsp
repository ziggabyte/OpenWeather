<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.WeatherBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ziggi's Weather App</title>
</head>
<body>

<h1>The weather!</h1>

<%
WeatherBean weatherBean = (WeatherBean) request.getAttribute("weatherBean");
String weatherReport = "The weather in " + weatherBean.getCity() + " is " + weatherBean.getClouds();

out.print(weatherReport);

%>


</body>
</html>