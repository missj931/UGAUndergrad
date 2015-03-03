<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href = "stylesheet.css"/>
<title>Browse</title>
	<meta name="author" content="your name" />
	<meta name="description" content="" />

<!-- link for CSS -->	
	<link rel="stylesheet" href="style.css" type="text/css" />
<!-- navigation -->



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
				<%=l%>
				


			</ul>
		</div>
		<h1>ResumeBook</h1>
	</div>

<div id= "content">
<%= request.getAttribute("table") %>
</div>
</body>

</html>