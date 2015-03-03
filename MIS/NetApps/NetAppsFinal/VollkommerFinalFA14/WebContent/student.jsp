<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="model.Academic"%>
<title>Student</title>


</head>
<body>
	<!-- Body -->

	<!-- retrieve table from request -->
	<%
		//get the message or table
		String t = "Failed to generate list of courses";

		//get user's permissions to properly display panel
		int permissions = 4;
		String nav = "";
		Academic user = new Academic("0", "0", "0", 4);
		try {
			user = (Academic) request.getSession().getAttribute("user");
			permissions = user.getPermissions();

		} catch (Exception e) {
			permissions = 4;
		}

		String m = "You do not belong on this page.";

		if (permissions != 4) {

			m = "Hi " + user.getFName() + " " + user.getLName();
		}

		if (request.getAttribute("table") != null) {
			m = (String) request.getAttribute("msg");
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

			if ((String) request.getAttribute("table") != null) {
				t = (String) request.getAttribute("table");
			}
		} catch (Exception e) {
			t = "Failed to generate student table";
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



	<h1>Student Page</h1>

	<!-- message -->
	<p><%=m%></p>

	<p>Students can:</p>
	<ol>
		<li>Add Classes</li>
		<li>Drop Classes</li>
		<li>Edit Their Account</li>
		<li>Delete Their Account</li>
	</ol>


	<h3>Update Account</h3>
	<p>If you update your email, you will have to log back in</p>
	<form name="Edit My Account" action="EditAccount" method="POST">

		Email:<input type="email" name="email" value='<%=user.getEmail()%>'
			required><br> First Name:<input type="text" name="first"
			value='<%=user.getFName()%>' required> Last Name: <input
			type="text" name="last" value='<%=user.getLName()%>' required><br>
		<input type="submit" name="UpdateAccount" value="Update">

	</form>
	<h3>Class Schedule</h3>
	<%=t%>

	<h3>Enroll in Course</h3>
	<form name="Add Course" action="EnrollCourse" method="POST">
		CRN:<input type="number" name="crn" min="100000" max="999999"
			value="000000" required><input type="submit" name="AddCourse"
			value="Add Course">

	</form>

	<h3>Delete Account</h3>
	<form name="Delete Account" action="DeleteAccount" method="POST">


		<input type="submit" name="Delete Account" value="Delete My Acocunt">

	</form>

</body>
</html>