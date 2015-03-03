package model;


import java.sql.PreparedStatement;


public class AdminTools extends DataHelper {




	//***************************************************************************
	//constructors
	/**
	 * Constructor
	 * establishes connection to the database
	 */

	public AdminTools(){
		super();

	}


	//**************************************************************************************************
	//public methods



	public String searchABC(String path){

		//Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM info ORDER BY L_NAME DESC";

		try {

			PreparedStatement ps = super.connection.prepareStatement(query);
			super.results = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		//parse the query result using a loop
		//create a table
		String table= "";
		table +="<div id='content'><table border=1>";
		ResumeHelper rh = new ResumeHelper();

		//add table headders
		table += "<tr> <th> Admin? </th> <th> Email </th> <th>Last Name</th> <th>First Name</th> <th>Major</th> <th>Resume</th> <th>Delete Resume</th> <th>Class</th> <th>Delete Account</th></tr>";
		try {

			//add data
			while(super.results.next()){


				rh.writeResume(results.getString("USER_NAME"),  path + "/" + results.getString("RESUME_NAME") + ".pdf");
				
				//new row
				table += " <tr>";
				String admin = "No";

				if(super.results.getInt("ADMIN") == 1){
					admin = "Yes";
				}

				//username
				table +=" <td>";
				table += admin;
				table +=" </td>";



				//username
				table +=" <td>";
				table += super.results.getString("USER_NAME");
				table +=" </td>";

				//last name
				table +=" <td>";
				table += super.results.getString("L_NAME");
				table +=" </td>";


				//F_NAME
				table +=" <td>";
				table += super.results.getString("F_NAME");
				table +=" </td>";


				//Major
				table +=" <td>";
				table += super.results.getString("MAJOR");
				table +=" </td>";




				table += " <td>";
				if(! results.getString("RESUME_NAME").isEmpty()){
					table += "<a href=resumes/" + results.getString("RESUME_NAME") + ".pdf" + "> Download Resume</a>";
					
				}else{
					table += "";
				}
				// Resume
				
				//Class
				table +=" <td>";
				table += "<form name='Delete Resume' action='AdminDeleteResume' method='POST'> <input type='hidden' name='userName' value='" +super.results.getString("USER_NAME") +"' > <input type='submit' value='Delete Resume'> </form>" ;
				
				table +=" </td>";
				

				//Class
				table +=" <td>";
				table += super.results.getString("CLASS_STANDING");
				table +=" </td>";

				//Delete
				table +=" <td>";
				table += "<form name='Delete Account' action='AdminDeleteUser' method='POST'> <input type='hidden' name='userName' value='" +super.results.getString("USER_NAME") +"' > <input type='submit' value='Delete Account'> </form>" ;
				table +=" </td>";

				

				//end row
				table +=" </tr>";

			}

			// end loop

			//an error has occured
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//end table
		table +=" </table></div>";


		//return table
		return table;



	}




	public String createPage(String table){

		String page ="";



		page += "<div id='header'>";
		page += "<div id='nav'>";
		page += "<ul>";


		page += "<li><a href='Index.jsp'>Home</a></li>";
		page += "<li><a href='Search.jsp'>Search</a></li>";
		page += "<li><a href='browse'>Browse</a></li>";
		page += "<li><a href='AdminPanel'>Admin</a></li>";
		page += "<li><a href='User.jsp'>User</a></li>";
		page += "<li><a href ='Login'>Log Out</a></li>";
		

		page += "</ul>";
		page += "</div>";
		page += "<h1>ResumeBook</h1>";
		page += "<p>Admin Page</p>";



		page += "</div>";

		page += "<div id='content'>";

		page += "<p>Welcome to ResumeBook Admin Page! This website is essentially facebook for ";
		page += "your resume! Once uploading your resume you will be connected to the ";
		page += "rest of the ResumeBook community. This will allow you to search and be searched by thousands ";
		page += "individuals and organizations world wide. Admin users have the ability to alter accounts as they see fit. </p>";
		page += "<p></p>";
		page += "</div>";
		page += "<div id='login'>";

		page += "<div id='footer'>";
		page += "</div>";
		page += "</div>";

		page += "</body><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>";
		page += "<title>Admin Page</title>";

		page += "<!--[if lt IE 9]><script src='//html5shim.googlecode.com/svn/trunk/html5.js'></script><![endif]--></head>";
		page += "<body><section id='container'><div id='signUp'>";
		page += "<h1>Create Account!</h1>";
		page += "<form name='createAcct' action='AdminCreate' method='post'>";

		page += "<input type='checkbox' name='isAdmin' value='1'> Is an Admin <br>";
		page += "<input type='email' name='userName' value='Email'" ;
		page += "placeholder='Username or Email' required> <input page += type='password' name='password' value='password' placeholder='Password' required> <br> <input type='text' name='fname' value='First Name' placeholder='First Name' required> <input type='text' name='lname' value='Last Name' placeholder='Last Name' required> <br> <select name='major' required> <option>Select Your Major</option> <option value='Accounting'>Accounting</option> <option value='Economics'>Economics</option> <option value='Finance'>Finance</option> <option value='Management'>Management</option><option value='Management Information System'>Management Information System</option><option value='Marketing'>Marketing</option><option value='Real Estate'>Real Estate</option><option value='Risk Management and Insurance'>Risk Management and Insurance</option></select> <select name='classStanding'  required><option>Select Your Class</option><option value='Freshmen'>Freshmen</option><option value='Sophomore'>Sophomore</option><option value='Junior'>Junior</option><option value='Senior'>Senior</option></select> <br> <input type='submit' name='commit' value='Create'></form></div></section>";

		page += "<title>Admin Page</title>";

		page += "<!--[if lt IE 9]><script src='//html5shim.googlecode.com/svn/trunk/html5.js'></script><![endif]--></head>";
		page += "<body><section id='container'><div id='signUp'>";
		page += "<h1>Update Account!</h1>";

		page += "<p> Enter the email of the user to be updated. Must matching existing user Email.  To keep the data the same, enter the exising info as in the table below.";


		page += "<form name='updtAcct' action='AdminUpdate' method='post'>";

		page += "<br><input type='checkbox' name='isAdmin' value='1'> Is an Admin <br>";
		page += "<input type='email' name='userName' value='Email'" ;

		page += "placeholder='Email' required>  <br> <input type='text' name='fname' value='First Name' placeholder='First Name' required> <input type='text' name='lname' value='Last Name' placeholder='Last Name' required> <br> <select name='major' required> <option>Select Your Major</option> <option value='Accounting'>Accounting</option> <option value='Economics'>Economics</option> <option value='Finance'>Finance</option> <option value='Management'>Management</option><option value='Management In"
				+ "ation System'>Management Information System</option><option value='Marketing'>Marketing</option><option value='Real Estate'>Real Estate</option><option value='Risk Management and Insurance'>Risk Management and Insurance</option></select> <select name='classStanding'  required><option>Select Your Class</option><option value='Freshmen'>Freshmen</option><option value='Sophomore'>Sophomore</option><option value='Junior'>Junior</option><option value='Senior'>Senior</option></select> <br> <input type='submit' name='commit' value='Update' required></form></div></section>";


		page += table;


		return page;

	}

}
