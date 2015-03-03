<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="model.Academic"%>
<title>Courses</title>
</head>
<!-- Body -->
<body>
	<!-- retrieve table from request -->
	<%
		//get the message or table
		String t = "Failed to generate list of courses";
		//get user's permissions to properly display panel
		int permissions = 4;
		String nav = "";
		try {
			Academic user = (Academic) request.getSession().getAttribute(
					"user");
			permissions = user.getPermissions();

		} catch (Exception e) {
			permissions = 4;
		}

		switch (permissions) {
		case 0: {
			nav += "<li>";
			nav += "<a href='student'>Student</a>";
			nav += "</li>";
			break;
		}
		case 1: {

			nav += "<li>";
			nav += "<a href='teacher'>Teacher</a>";
			nav += "</li>";
			break;
		}
		case 2: {
			nav += "<li>";
			nav += "<a href='admin'>Admin</a>";
			nav += "</li>";
			break;
		}
		}//end switch
		if (permissions < 4 && permissions >= 0) {
			nav += "<li>";
			nav += "<a href='Logout'>Sign Out</a>";
			nav += "</li>";
		}

		//get the message from the servlet. if an error occurs, no message was forwarded from server, so display defualt message
		try {

			String table = (String) request.getAttribute("table");

			if (!table.isEmpty()) {
				t = table;
			}
		} catch (Exception e) {
			t = "Failed to generate list of courses";
		}
	%>


	<!-- Navigation -->
	<div class='nav'>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="browse">Course List</a></li>
			<%=nav%>
		</ul>
	</div>

	<h1>Course List</h1>

	<h3>Search</h3>
	<form name="Search" action="search" method="get">



		Minimum:<input type="number" name="min" min="100000" max="999999"
			value="100000" required><br> Maximum:<input
			type="number" name="max" min="100000" max="999999" value="999999"
			required><br> <input type="submit" name="search"
			value="search">

	</form>

	<!-- display message or table -->
	<h3>table</h3>
	<%=t%>



</body>
</html>