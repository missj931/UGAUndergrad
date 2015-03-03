<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- import classes -->
<%@ page import="model.Amortization"%>
<%@ page import="model.Loan"%>

<!-- include style for table -->
<link rel="stylesheet" type="text/css" href="Style.css">



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Amortized</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amortization</title>

<h1>Vollkommer's Loan Amortization Application - Table</h1>
<body>

	<!-- Get data from servlet -->
	<%
		String msg = (String)request.getAttribute("theResponse");
		
	%>

<%= msg %>
</body>
</html>