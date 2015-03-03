<!DOCTYPE html>
<html>
<head>
<!-- link for CSS -->
<link type="text/css" rel="stylesheet" href="stylesheet.css" />
<!-- index -->

<title>Home</title>

<meta name="Matthew Vollkommer" content="your name" />
<meta name="This is the home page for our project" content="" />





</head>

<!-- display message to user if any exist -->

<%
      String p = "";
      try{if (!request.getSession().getAttribute("msg").equals("")) {
            p = "<p> " + request.getSession().getAttribute("msg") + "</p>";
      }
      }catch(Exception e){
            
      }
%>

<%=p%>

<body>


      <!-- webpage content -->


	<div id="header">
		<div id="nav">
			<ul>
				<li><a href="Index.jsp">Home</a></li>
				<li><a href="Search.jsp">Search</a></li>
				<li><a href="browse">Browse</a></li>

	
				<%
					String s = "";
					String l = "";
					String a = "";

					try {
						
						if (request.getSession().getAttribute("isAdmin").equals("1")) {
							a = "<li><a href='AdminPanel'>Admin</a></li>";
						}
						
						if (!request.getSession().getAttribute("userName").equals("")) {
							s = "<li><a href='User.jsp'>User</a></li>";
							l = "<li><a href ='Login'>Log Out</a></li>";
						}

						
					} catch (Exception e) {

					}
				%>
				<%= a %>
				<%=s%>
				<%=l%>


			</ul>
		</div>
		<h1>ResumeBook</h1>
		<p>Login to Web App</p>
		<form name="login" action="Login" method="post">
			<p>
				<input type="email" name="userName" value="Email"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
			</p>
			<p>
				<input type="password" name="password" value="Password" required>
			</p>

			<p class="submit">
				<input type="submit" name="commit" value="Login">
			</p>
		</form>
	</div>

	<div id="content">

            <p>Welcome to ResumeBook! This website is essentially facebook for
                  your resume! Once uploading your resume you will be connected to the
                  rest of the ResumeBook community. This will allow you to search and
                  be searched by thousands individuals and organizations world wide.
                  Login to your account above, or create a new account bellow for free.</p>
            <p></p>
      </div>
      <div id="login">

            <div id="footer"></div>
      </div>

</body>

<!-- Create Account -->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Login Form</title>
<link rel="stylesheet" href="css/style.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
      <section id="container">
            <div id="signUp">
                  <h1>Sign up!</h1>
                  <form name="createAcct" action="CreateAccount" method="post">

                        <input type="email" name="userName" value="Email"
                              placeholder="Email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                              required> <input
                              type="password" name="password" value="password"
                              placeholder="Password" required> <br> <input
                              type="text" name="fname" value="First Name"
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
                        <input type="submit" name="commit" value="Create" required>

                  </form>

            </div>


      </section>
</body>

</html>
