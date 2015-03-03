package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Academic;
import model.Course;

public class DataHelper {

	// **********************************
	// class variables
	// protected class variables
	protected ResultSet results;
	protected Connection connection;

	// end class variables
	// ********************************

	// ***************************************************************************
	// constructors
	/**
	 * @cunstructor
	 * Defualt connection to database. Connect root personto findal at
	 * jdbc:mysql://localhost:3306/
	 */
	public DataHelper() {


		String dbName = "findal";
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


	/**
	 * @constructor
	 * connects to the database using the supplied info at
	 * jdbc:mysql://localhost:3306/
	 * 
	 * @param dbName
	 * @param uname
	 * @param pwd
	 */
	public DataHelper(String dbName, String uname, String pwd) {
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// set up the driver
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, uname, pwd);

		} catch (Exception e) {

			// failed to connect to database. do nothing

		}

	}

	// end constructors
	// ****************************************************************************

	//***************************************************************************************************************
	//Methods

	//***************************************************************
	//private methods
	/**
	 * connects to the defualt database
	 */
	private void connect(){
		/**
		 * Constructor establishes connection to the database
		 */

		String dbName = "findal";
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
	//end private methods
	//*******************************************************************


	// *****************************************************************
	// public methods

	/**
	 * creates a course record
	 * 
	 * @param course
	 *            , the course to be created
	 * @return true if succesful, false if unsuccesful
	 */
	public boolean create(Course course) {
		// initialize local variables
		boolean success = false;
		connect();

		String query = "INSERT INTO courses (CRN, NAME) VALUES(?, ?)";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, course.getCRN());
			ps.setString(2, course.getName());

			// execute update
			ps.executeUpdate();




			success = true;
			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful

		}
		return success;
	}

	/**
	 * get the admin table
	 * 
	 * @return the the html table if successful or a message if unsuccesful
	 */
	public String adminTable() {
		// initialize local variables
		String table = "";
		connect();
		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM courses ORDER BY CRN ASC";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			// execute query
			results = ps.executeQuery();


			table += "<h4>Course</h4>";
			// parse the query result using a loop
			// create a table with html tags
			table += "<table border=1>";

			// add table headders
			table += "<tr> <th>CRN</th><th>Name</th><th>Delete Course</th></tr>";

			// add data
			while (results.next()) {

				Course course = new Course(results.getInt("CRN"), results.getString("NAME"));

				// start row
				table += "<tr>";

				// CRN
				table += "<td>";
				table += course.getCRN();
				table += "</td>";

				//NAME
				table += "<td>";
				table += course.getName();
				table += "</td>";

				// delete class
				table += "<td>";
				table += "<form name='Delete Course' action='DeleteCourse' method='POST'> <input type='hidden' name='crn' value='"
						+ course.getCRN()
						+ "'> <input type='submit' value='Delete Class'> </form>";
				table += "</td>";

				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";




			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table += "<p>An error record when parsing results from the database.</p>";

		}

		connect();
		// Query database for users ORDER BY L_NAME
		query = "SELECT * FROM users ORDER BY PERMISSIONS ASC, L_NAME ASC";
		// reset boolean flag


		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			// execute query
			results = ps.executeQuery();


			table += "<h4>User Accounts</h4>";
			// parse the query result using a loop
			// create a table with html tags
			table += "<table border=1>";

			// add table headders
			table += "<tr> <th>User Type</th><th>Last Name</th><th>First Name</th><th>Email</th><th>Delete Account</th></tr>";

			// add data
			while (results.next()) {

	
				Academic account = new Academic(
						results.getString("F_NAME"),
						results.getString("L_NAME"),
						results.getString("EMAIL"),
						results.getInt("PERMISSIONS"));

				if(account.getEmail().equals("TBA")){
					//do not print out the TBA account
					//it is used as a placeholder for 
					//courses without a teacher. it is not a user
					continue;
				}
				
				// start row
				table += "<tr>";

				// Permission level
				table += "<td>";
				table += account.getPermissions();
				table += "</td>";

				// Last Name
				table += "<td>";
				table += account.getLName();
				table += "</td>";

				// First Name
				table += "<td>";
				table += account.getFName();
				table += "</td>";

				// Email
				table += "<td>";
				table += account.getEmail();
				table += "</td>";
				

				// delete Academic
				table += "<td>";
				
				if(account.getPermissions() == 2){
					table += "Can't delete Admins";
				}else{
				table += "<form name='Delete account' action='DeleteAccount' method='POST'> <input type='hidden' name='user' value='"
						+ account.getEmail()
						+ "'> <input type='submit' value='Delete Account'> </form>";
				table += "</td>";
				}
				
				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";


			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table += "<p>An error record when parsing results from the database.</p>";
		}


		// return table
		return table;

	}
	/**
	 * get the teacher's records
	 * @param teacher, the teacher whose records you are requesting
	 * @return an HTML table if success, an html paragraph if fail
	 */
	public String teacherTable(Academic teacher) {
		// initialize local variables

		connect();
		String table = "";
		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM browse_view WHERE TEACHER_EMAIL = ?";
		//many to many databases queried to create the view on the database side
		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, teacher.getEmail());

			// execute query
			results = ps.executeQuery();


			// parse the query result using a loop
			// create a table with html tags
			table = "<table border=1>";

			// add table headders
			table += "<tr> <th>CRN</th><th>Course Name</th><th>Students</th> <th>Drop Course</th></tr>";


			// add data
			while (results.next()) {

				int students = results.getInt("ENROLLED");
				Course course = new Course(results.getInt("CRN"), results.getString("NAME"));

				// start row
				table += "<tr>";

				// CRN
				table += "<td>";
				table += course.getCRN();
				table += "</td>";


				// Course name
				table += "<td>";
				table += course.getName();
				table += "</td>";

				// students
				table += "<td>";
				table += students;
				table += "</td>";

				// drop class
				table += "<td>";
				table += "<form name='Drop Course' action='DontTeach' method='POST'> <input type='hidden' name='crn' value='"
						+ course.getCRN()
						+ "' > <input type='hidden' name='teacher' value='"
						+ teacher
						+ "' > <input type='submit' value='Drop Class'> </form>";
				table += "</td>";

				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";



			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table = "<p>An error record when parsing results from the database.</p>";
		}


		// return table
		return table;

	}

	/**
	 * returns the classes and number of students a account has
	 * 
	 * @param student
	 *            , the student getting their courses
	 * @return the the html table if successful or a message if unsuccesful
	 */
	public String studentTable(Academic student) {
		// initialize local variables

		String table = "";
		connect();
		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM student_view WHERE EMAIL = ?";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, student.getEmail());

			// execute query
			results = ps.executeQuery();



			// parse the query result using a loop
			// create a table with html tags
			table = "<table border=1>";

			// add table headders
			table += "<tr> <th>CRN</th> <th>Course Name</th><th>Teacher First Name</th><th>Teacher Last Name</th><th>Teacher Email</th> <th>Drop Course</th></tr>";

			// add data
			while (results.next()) {

				Academic teacher = new Academic(results.getString("F_NAME"), results.getString("L_NAME"), results.getString("TEACHER_EMAIL"));


				Course course = new Course(results.getInt("CRN"), results.getString("NAME"));

				// start row
				table += "<tr>";

				// CRN
				table += "<td>";
				table += course.getCRN();
				table += "</td>";

				// Course name
				table += "<td>";
				table += course.getName();
				table += "</td>";


				// Teacher first Name
				table += "<td>";
				table += teacher.getFName();
				table += "</td>";

				// Teacher's last name
				table += "<td>";
				table += teacher.getLName();
				table += "</td>";

				// Teacher's email
				table += "<td>";
				table += teacher.getEmail();
				table += "</td>";

				// drop class
				table += "<td>";
				table += "<form name='Drop Course' action='DropCourse' method='POST'> <input type='hidden' name='crn' value='"
						+ course.getCRN()
						+ "' > <input type='hidden' name='student' value='"
						+ student.getEmail()
						+ "' > <input type='submit' value='Drop Class'> </form>";
				table += "</td>";

				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";

			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table = "<p>An error record when parsing results from the database.</p>";
		}

		// return table
		return table;

	}

	/**
	 * updates the course
	 * 
	 * @param oldCourse
	 *            , the Course being changed
	 * @param newCourse
	 *            , the new course information
	 * @return true for successful, false for unsuccessful
	 */
	public boolean update(Course oldCourse, Course newCourse) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "UPDATE courses SET CRN = ? WHERE CRN = ?";

		// query database

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, newCourse.getCRN());
			ps.setInt(2, oldCourse.getCRN());

			// execute query
			ps.executeUpdate();

			// query was successful
			success = true;



			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful

		}
		// inform calling object if update was successfull
		return success;
	}

	/**
	 * updates the user record
	 * 
	 * @param acount
	 *            , the user being updated
	 * @return true if succesful, false if unsuccessful
	 */
	public boolean update(Academic user, String email) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "UPDATE users SET EMAIL = ?, L_NAME = ?, F_NAME = ? WHERE EMAIL = ?";

		// query database
		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, user.getLName());
			ps.setString(3, user.getFName());

			ps.setString(4, user.getEmail());

			// execute query
			ps.executeUpdate();

			// query was successful
			success = true;

			ps.close();
			connection.close();


		} catch (Exception e) {
			// query was not successful

		}
		// tell the calling object if the update was successful
		return success;
	}

	/**
	 * deletes the Course from the database
	 * @param course, the account to be deleted
	 * @return true if successful, false if unsuccesful
	 */
	public boolean delete(Course course) {
		// initialize local variables
		boolean success = false;



		connect();
		String query = "DELETE FROM courses WHERE CRN = ? ";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, course.getCRN());

			// execute query
			ps.executeUpdate();

			success = true;




			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful

		}

		// tell the calling object if the update was successful
		return success;

	}

	/**
	 * deletes the Account from the database
	 * 
	 * @param account
	 *            , the account to be deleted
	 * @return true if successful, false if unsuccesful
	 */
	public boolean delete(Academic account) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "DELETE FROM users WHERE EMAIL =?";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, account.getEmail());

			// execute query
			ps.executeUpdate();

			success = true;

			ps.close();
			connection.close();

		} catch (Exception e) {
			// query was not successful

		}

		// tell the calling object if the update was successful
		return success;

	}
	/**
	 * enroll a student in a course
	 * @param student, the student who wants to add a class
	 * @param course, the course the student wants to add
	 * @return true for success, false for fail
	 */
	public boolean addStudent(Academic student, Course course) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "INSERT INTO enrollment (CRN, EMAIL) VALUES (?, ?)";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, course.getCRN());
			ps.setString(2, student.getEmail());

			// execute query
			ps.executeUpdate();

			success = true;


			ps.close();
			connection.close();


		} catch (Exception e) {
			// query was not successful

		}

		// tell the calling object if the update was successful
		return success;

	}

	/**
	 * drops a teacher from a coursee
	 * @param teacher, the teacher who wants to drop a class
	 * @param course, the course the teacher wants to drop
	 * @return true for successful, false for unsuccessful
	 */
	public boolean dontTeach(Academic teacher, Course course) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "UPDATE courses SET TEACHER_EMAIL = ? WHERE CRN = ?";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, "TBA");
			ps.setInt(2, course.getCRN());

			// execute query
			ps.executeUpdate();

			success = true;



			ps.close();
			connection.close();

		} catch (Exception e) {
			// query was not successful

		}

		// tell the calling object if the update was successful
		return success;

	}

	/**
	 * 
	 * @param teacher, the teacher
	 * @param course, the course the teacher wants to teach
	 * @return true for successful, false for unsuccessful
	 */
	public boolean addTeacher(Academic teacher, Course course) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "UPDATE courses SET TEACHER_EMAIL = ? WHERE CRN = ?";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, teacher.getEmail());
			ps.setInt(2, course.getCRN());

			// execute query
			ps.executeUpdate();


			success = true;



			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful

		}

		// tell the calling object if the update was successful
		return success;

	}

	/**
	 * removes a student from a course
	 * @param student, the student looking to drop a class
	 * @param course, the class the student wishes to drop
	 * @return true if successful, false if unsuccessful
	 */
	public boolean dropCourse(Academic student, Course course) {
		// initialize local variables
		boolean success = false;
		connect();
		String query = "DELETE FROM enrollment WHERE CRN = ? AND email =?";

		try{
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, course.getCRN());
			ps.setString(2, student.getEmail());

			ps.executeUpdate();
			success = true;

			ps.close();
			connection.close();

		}catch(Exception e){

		}

		// tell the calling object if the update was successful
		return success;

	}

	/**
	 * get's the user with the email provided
	 * @param userName, the user's email
	 * @return the user as an Academic object
	 */
	public Academic getAcademic(String userName) {
		Academic account = new Academic();
		connect();
		String query = "SELECT * FROM users WHERE email = ?";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, userName);

			// execute query
			results = ps.executeQuery();
			results.next();

			account = new Academic(results.getString("F_NAME"),
					results.getString("L_NAME"), results.getString("EMAIL"),
					results.getInt("PERMISSIONS"));

			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful
			account = new Academic("0", "0", "0", 4);
		}

		return account;

	}

	/**
	 * does a user exist with this username and password?
	 * @param username, the user's email address
	 * @param password, the user's password
	 * @return true to login, false if login fail
	 */
	public Academic login(String username, String password) {
		connect();
		int pswd = password.hashCode();
		String query = "SELECT * FROM users WHERE EMAIL = ? AND PASSWORD = ?";
		Academic person = new Academic();
		;

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, pswd);

			// execute query
			results = ps.executeQuery();
			results.next();

			person = new Academic(results.getString("F_NAME"),
					results.getString("L_NAME"), results.getString("EMAIL"),
					results.getInt("PERMISSIONS"));

			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful
			person = new Academic("0", "0", "0", 4);

		}

		return person;

	}

	/**
	 * returns a list of courses
	 * @return an html table
	 */
	public String browse() {
		// initialize local variables

		connect();
		String table = "";
		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM browse_view";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			// execute query
			results = ps.executeQuery();


			// parse the query result using a loop
			// create a table with html tags
			table = "<table border=1>";

			// add table headders
			table += "<tr><th>CRN</th><th>Course Name</th><th>Teacher Name</th> <th>Teacher Email</th></tr>";


			// add data
			while (results.next()) {

				Course course = new Course(results.getInt("CRN"), results.getString("NAME"));
				Academic teacher = new Academic(results.getString("F_NAME"), results.getString("L_NAME"), results.getString("TEACHER_EMAIL"));
				// start row
				table += "<tr>";

				// CRN
				table += "<td>";
				table += course.getCRN();
				table += "</td>";

				//Course Name
				table += "<td>";
				table += course.getName();
				table += "</td>";

				//Teacher Name
				table += "<td>";
				table += teacher.getLName() + ", "+ teacher.getFName();
				table += "</td>";

				//Teacher Email
				//Course Name
				table += "<td>";
				table += teacher.getEmail();
				table += "</td>";

				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";
			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table = "<p>An error record when parsing results from the database.</p>";
		}



		// return table
		return table;

	}

	/**
	 * search the table
	 * @param minimum, lowest CRN Number
	 * @param maximum, highest CRN Number
	 * @return html table for success html paragraph if fail
	 */
	public String browse(int minimum, int maximum) {
		// initialize local variables

		connect();
		String table = "";
		// Query database for users ORDER BY L_NAME
		String query = "SELECT * FROM browse_view WHERE CRN >= ? AND CRN <= ?";
		//many to many realated tables are queried on the view, database side

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, minimum);
			ps.setInt(2, maximum);
			// execute query
			results = ps.executeQuery();


			// parse the query result using a loop
			// create a table with html tags
			table = "<table border=1>";

			// add table headders
			table += "<tr><th>CRN</th><th>Course Name</th><th>Teacher Name</th> <th>Teacher Email</th></tr>";


			// add data
			while (results.next()) {

				Course course = new Course(results.getInt("CRN"), results.getString("NAME"));
				Academic teacher = new Academic(results.getString("F_NAME"), results.getString("L_NAME"), results.getString("TEACHER_EMAIL"));
				// start row
				table += "<tr>";

				// CRN
				table += "<td>";
				table += course.getCRN();
				table += "</td>";

				//Course Name
				table += "<td>";
				table += course.getName();
				table += "</td>";

				//Teacher Name
				table += "<td>";
				table += teacher.getLName() + ", "+ teacher.getFName();
				table += "</td>";

				//Teacher Email
				//Course Name
				table += "<td>";
				table += teacher.getEmail();
				table += "</td>";

				// end row
				table += "</tr>";

			}

			// end loop
			// end table
			table += " </table>";
			ps.close();
			connection.close();
			// an error has occured
		} catch (Exception e) {
			// an error occured, alert user
			table = "<p>An error record when parsing results from the database.</p>";
		}



		// return table
		return table;

	}

	/**
	 * adds a teacher to a course
	 * @param teacher, the teacher being added
	 * @param course, the course the teacher wants to teach
	 * @return
	 */
	public boolean teachCourse(Academic teacher, Course course){
		// initialize local variables
		boolean success = false;
		connect();
		String query = "UPDATE courses SET (TEACHER_EMAIL) VALUES(?) WHERE CRN = ?";

		try{
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, teacher.getEmail());
			ps.setInt(2, course.getCRN());
			ps.executeUpdate();
			success = true;


			ps.close();
			connection.close();
		}catch(Exception e){

		}

		// tell the calling object if the update was successful
		return success;
	}
	/**
	 * creates a new account with the person and the password 
	 * 
	 * @param person, the new user
	 * @param password, the new user's password
	 * @return
	 */
	public boolean create(Academic person, String password) {
		connect();
		// initialize local variables
		boolean success = false;
		// Query database for users ORDER BY L_NAME
		String query = "INSERT INTO users (EMAIL, PASSWORD, PERMISSIONS, L_NAME, F_NAME) VALUES(?, ?, ?, ?, ?)";

		try {
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, person.getEmail());
			ps.setInt(2, password.hashCode());
			ps.setInt(3, person.getPermissions());
			ps.setString(4, person.getLName());
			ps.setString(5, person.getFName());

			// execute query
			ps.executeUpdate();
			success = true;


			ps.close();
			connection.close();
		} catch (Exception e) {
			// query was not successful

		}


		// tell the calling object if the create was successful
		return success;
	}

	// End Public Methods
	// *************************************************************

	//End MEthods
	//********************************************************************************************************************

}//class
