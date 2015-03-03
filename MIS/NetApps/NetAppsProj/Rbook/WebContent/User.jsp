<!DOCTYPE html>

<html>

<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css" />
<title>User Account</title>

</head>

<body>


	<div id="header">
		<div id="nav">
			<ul>
				<li><a href="Index.jsp">Home</a></li>
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
				
				<li><a href="User.jsp">User</a></li>
				<li><a href="Login">Log Out</a></li>
				
			</ul>
		</div>
		<h1>ResumeBook</h1>
	</div>

	<!-- display message to user if any exist -->
	<p>
		<%=(String) request.getAttribute("msg")%>
	</p>


	
	<h1> <%=(String) request.getSession().getAttribute("fname")%>  <%=(String) request.getSession().getAttribute("lname")%></h1>
	<h3>
		Email:
		<%=(String) request.getSession().getAttribute("userName")%></h3>
	<h3>
		Major:
		<%=(String) request.getSession().getAttribute("major")%>
	</h3>
	<h3>
		Class Standing:
		<%=(String) request.getSession().getAttribute(
					"classStanding")%></h3>

<body>
	
	<p>To update your account, enter your information below. Please
		remember that fields left empty will be returned to their default
		values.</p>
	<section id="container">
		<div id="signUp">
			<h1>Update Account!</h1>

			<form name="updateAcct" action="UpdateAccount" method="post">

				<input type="text" name="fname" value="First Name"
					placeholder="First Name" required> <input type="text"
					name="lname" value="Last Name" placeholder="Last Name" required>
				<br> <select name="major" required>
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
				</select> <select name="classStanding" required>
					<option>Select Your Class</option>
					<option value="Freshmen">Freshmen</option>
					<option value="Sophomore">Sophomore</option>
					<option value="Junior">Junior</option>
					<option value="Senior">Senior</option>
				</select> <br> <input
					type="submit" name="commit" value="Update" required>

			</form>

		</div>

		<div id=uploadresume>
			<form enctype='multipart/form-data' action='UploadResume'
				method='post'>
				<label>Name:</label> <input type='text' name='name'><br />
				<label>Choose a file to upload:</label><br />
				<!-- The next input component is the file input type -->
				<input type='file' name='file'><br /> <input type='submit'
					value='Upload'>
			</form>
		</div>
		
		<div id =delete>
		Account:
		<form name="Delete" action='DeleteUser' method='post'>
				<input type='submit'
					value='Delete Account'>
			</form>
		Resume:	
			<form name="Delete" action='DeleteResume' method='post'>
				<input type='submit'
					value='Delete Resume'>
			</form>
		</div>






	</section>
</body>
</html>