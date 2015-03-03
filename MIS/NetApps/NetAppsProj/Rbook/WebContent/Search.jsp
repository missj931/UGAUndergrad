<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- link for CSS -->
<link type="text/css" rel="stylesheet" href="stylesheet.css" />
<!-- index -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="header">
		<div id="nav">
			<ul>
				<li><a href='Index.jsp'>Home</a></li>
				<li><a href="Search.jsp">Search</a></li>
				<li><a href="browse">Browse</a></li>
				
				<%
					String a = "";
					try {
						if (request.getSession().getAttribute("isAdmin").equals("1")) {
							a = "<li><a href='AdminPanel'>Admin</a></li>";
						}
					} catch (Exception e) {
						
					}
				%>
                   <%=a %>
				
				
				<%
					String s = "";
					String l = "";

					try {
						if (!request.getSession().getAttribute("userName").equals("")) {
							s = "<li><a href='User.jsp'>User</a></li>";
							l = "<li><a href ='Login'>Log Out</a></li>";
						}
					} catch (Exception e) {

					}
				%>
                        <%=s%>
                        <%=l %>
                        

			</ul>	
		</div>
		<h1>ResumeBook</h1>
	</div>
	
	<div id="content">
	<h1>Search</h1>
			<form name="search" action="Search" method="post">

				<br> <select name="major" 
					required>
					<option>Select Your Major</option>
					<option value="Accounting">Accounting</option>
					<option value="Economics">Economics</option>
					<option value="Finance">Finance</option>
					<option value="Management">Management</option>
					<option value="Management Information System">Management
						Information System</option>
					<option value="Marketing">Marketing</option>
					<option value="Real Estate">Real Estate</option>
					<option value="Risk Management and Insurance">Risk
						Management and Insurance</option>
				</select> <select name="classStanding"  required>
					<option>Select Your Class</option>
					<option value="Freshmen">Freshmen</option>
					<option value="Sophomore">Sophomore</option>
					<option value="Junior">Junior</option>
					<option value="Senior">Senior</option>
				</select> 

				<br> <input type="submit" name="commit" value="Search" >
				
				</div>
</body>
</html>