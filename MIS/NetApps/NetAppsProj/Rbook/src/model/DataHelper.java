package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataHelper {

	// **********************************
	// class variables
	protected ResultSet results;
	protected Connection connection;

	// end class variables
	// ********************************

	// ***************************************************************************
	// constructors
	/**
	 * Constructor establishes connection to the database
	 */

	public DataHelper() {

		String dbName = "resume_book";
		String uname = "root";
		String pwd = "";
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// set up the driver
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public DataHelper(String dbName, String uname, String pwd) {
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// set up the driver
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// end constructors
	// ***********************************************************************************************

	// **************************************************************************************************
	// public methods
	public String search(String major, String classStatus, String path) {
		// create a table
		String table = "";

		String query = "SELECT * FROM info ";
		try {
			// Query database for users ORDER BY L_NAME
			if(classStatus.equals("") &&  major.equals("")){
				query += " ORDER BY L_NAME DESC";
			}else if(classStatus.equals("")){
				query += " WHERE MAJOR = ? ORDER BY L_NAME DESC";
			}else if(major.equals("")){
				query += " WHERE CLASS_STANDING = ? ORDER BY L_NAME DESC";
			}else{

				query += " WHERE Major = ? AND CLASS_STANDING = ? ORDER BY L_NAME DESC";
			}

			PreparedStatement ps = connection.prepareStatement(query);


			//No criteria given
			if(classStatus.equals("") &&  major.equals("")){
				


			}else if(major.equals("")){
				//no major  given
				// fill in the prepared statement
				ps.setString(1, classStatus);




			}else if(classStatus.equals("")){
				//no classStatus given
				// fill in the prepared statement
				ps.setString(1, major);
				// fill in the prepared statement


			}else{

				// fill in the prepared statement
				ps.setString(1, major);
				ps.setString(2, classStatus);
			}




			// execute the query
			results = ps.executeQuery();

			// parse the query result using a loop

			table += "<table border=1>";

			// add table headders
			table += "<tr> <th> Email </th> <th>Last Name</th> <th>First Name</th> <th>Major</th> <th>Resume</th> <th>Class</th> </tr>";

			ResumeHelper rh = new ResumeHelper();
			
			// add data
			while (results.next()) {

				rh.writeResume(results.getString("USER_NAME"),  path + "/" + results.getString("RESUME_NAME") + ".pdf");
				// new row
				table += " <tr>";


				//USER NAME
				table +=" <td>";
				table += results.getString("USER_NAME");
				table +=" </td>";

				// last name
				table += " <td>";
				table += results.getString("L_NAME");
				table += " </td>";

				// F_NAME
				table += " <td>";
				table += results.getString("F_NAME");
				table += " </td>";

				// Major
				table += " <td>";
				table += results.getString("Major");
				table += " </td>";

				
				table += " <td>";
				if(! results.getString("RESUME_NAME").isEmpty()){
					table += "<a href=resumes/" + results.getString("RESUME_NAME") + ".pdf > Download Resume</a>";
					
				}else{
					table += "";
				}
				// Resume
				
				table += " </td>";

				// Class
				table += " <td>";
				table += results.getString("CLASS_STANDING");
				table += " </td>";

				// end row
				table += " </tr>";

			}

			// end loop

			// an error has occured
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// end table
		table += " </table>";

		// return table
		return table;

	}

	/**
	 * 
	 * @return an HTML Formated table sorted in alphabetical order by Name
	 */
	public String searchABC(String path) {

		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM info ORDER BY L_NAME DESC";

		try {

			PreparedStatement ps = connection.prepareStatement(query);
			results = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// parse the query result using a loop
		// create a table
		String table = "";
		table += "<table border=1>";

		// add table headders
		table += "<tr> <th> Email </th> <th>Last Name</th> <th>First Name</th> <th>Major</th> <th>Resume</th> <th>Class</th> </tr>";

		ResumeHelper rh = new ResumeHelper();
		try {

			// add data
			while (results.next()) {

				
				rh.writeResume(results.getString("USER_NAME"),  path + "/" + results.getString("RESUME_NAME") + ".pdf");
				
				// new row
				table += " <tr>";

				//USER NAME
				table +=" <td>";
				table += results.getString("USER_NAME");
				table +=" </td>";

				// last name
				table += " <td>";
				table += results.getString("L_NAME");
				table += " </td>";

				// F_NAME
				table += " <td>";
				table += results.getString("F_NAME");
				table += " </td>";

				// Major
				table += " <td>";
				table += results.getString("MAJOR");
				table += " </td>";

				
				//resume
				
				table += " <td>";
				if(! results.getString("RESUME_NAME").isEmpty()){
					table += "<a href=resumes/" + results.getString("RESUME_NAME") + ".pdf> Download Resume</a>";
					
				}else{
					table += "";
				}
				
				
				
				table += " </td>";
				// Class
				table += " <td>";
				table += results.getString("CLASS_STANDING");
				table += " </td>";


				// end row
				table += " </tr>";

			}

			// end loop

			// an error has occured
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// end table
		table += " </table>";

		// return table
		return table;

	}

	public boolean deleteUser(String username) {
		boolean success = true;
		
		// query to delete user
		// string to hold query
		String query = "DELETE FROM info WHERE USER_NAME= ?";

		try {
			// prepared statement using our query string
			PreparedStatement ps = connection.prepareStatement(query);

			// fill in the prepared statement
			ps.setString(1, username);

			// execute the query
			ps.executeUpdate();

		} catch (Exception e) {
			success = false;
		}
		return success;
	}


	public boolean updateUser(String userName, String firstName,
			String lastName, String major,
			String classStanding) {

		//initialize variables
		boolean success = false;

		



		String query = "UPDATE info SET L_NAME = ?, F_NAME = ?, MAJOR = ?, CLASS_STANDING = ? WHERE USER_NAME = ?";

	
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);



			ps.setString(1, lastName);
			ps.setString(2, firstName);
			ps.setString(3, major);
			ps.setString(4, classStanding);
			ps.setString(5, userName);

			ps.executeUpdate();
			success = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// return if successful or not

		return success;

	}

	public boolean updateUser(String userName, String firstName,
			String lastName, boolean isAdmin, String major,
			String classStanding) {

		//initialize variables
		int admin = 0;
		boolean success = false;

		
		//is the uer an admin?
		if(isAdmin){
			//yes
			admin = 1;
		}
		


		String query = "UPDATE info SET L_NAME = ?, F_NAME = ?, ADMIN = ?, MAJOR = ?, CLASS_STANDING = ? WHERE USER_NAME = ?";

	
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);



			ps.setString(1, lastName);
			ps.setString(2, firstName);
			ps.setInt(3, admin);
			ps.setString(4, major);
			ps.setString(5, classStanding);
			ps.setString(6, userName);

			ps.executeUpdate();
			success = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// return if successful or not

		return success;

	}

	public boolean createUser(String userName, String firstName,
			String lastName, boolean isAdmin, String major,
			String classStanding, String password) {
			
		//initialize variables
		int admin = 0;
		boolean success = false;

		
		//is the uer an admin?
		if(isAdmin){
			//yes
			admin = 1;
		}
		


		String query = "INSERT INTO info (USER_NAME, L_NAME, F_NAME, ADMIN, PASSWORD, MAJOR, CLASS_STANDING) VALUES (?, ?, ?, ?, ?, ?, ?)";

		// if no, create a new user

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);


			ps.setString(1, userName);
			ps.setString(2, lastName);
			ps.setString(3, firstName);
			ps.setInt(4, admin);
			admin = password.hashCode();
			ps.setInt(5, admin);
			ps.setString(6, major);
			ps.setString(7, classStanding);

			ps.executeUpdate();
			success = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// return if successful or not

		return success;

	}

	// end public methods
	// **********************************************************************************

	// end private methods
	// **********************************************************************************************

}// end class