<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- import classes -->
<%@ page import="myPackage.Amortization"%>
<%@ page import="myPackage.Loan"%>

<!-- include style for table -->
<link rel="stylesheet" type="text/css" href="Style.css">

<%
	//******************************
	//process input

	//initialize loan object
	try {
		Loan loan = new Loan(Double.parseDouble(request
				.getParameter("principal")), Double.parseDouble(request
				.getParameter("rate")), Double.parseDouble(request
				.getParameter("term")));

		//initialize amortiztation object
		Amortization amort = new Amortization(loan);
		//************************************************

		//**************************************
		//process output
		//amortize the loan
		out.write(amort.doAmortization());
		//***********************************************

		//catch any exceptions
	} catch (Exception e) {
		//alert the user
		out.write("An Error occured, please input appropriate values");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amortization</title>

<h1>Vollkommer's Loan Amortization Application - Table(with JSP only)</h1>

<body>

</body>
</html>