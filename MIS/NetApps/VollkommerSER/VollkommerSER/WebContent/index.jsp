<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amortization</title>

<h1>Vollkommer's Loan Amortization Application - Table(with JSP only)</h1>

</head>


<body>

	<!-- get user input and pass along to Amortization.jsp -->

	<form name='myForm' action='Amortization.jsp' method='get'>

		Principal in US dollars: <input type=text name=principal><br>
		Term in years: <input type=text name=term><br> Rate as a
		percent: <input type=text name=rate> <input type=submit
			value="Amortize!"><br />

	</form>
<body>

</body>
</html>