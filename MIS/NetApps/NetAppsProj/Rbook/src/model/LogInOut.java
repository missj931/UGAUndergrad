
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInOut {


	//**********************************
	//class variables
	private ResultSet results;
	private Connection connection;
	//end class variables
	//********************************

	public LogInOut(){


		String dbName = "resume_book";
		String uname = "root";
		String pwd = "";
		String url = "jdbc:mysql://localhost:3306/" + dbName;


		// set up the driver
		try{


			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, uname, pwd);

		}catch(Exception e){

			e.printStackTrace();

		}


	}


	public boolean isAdmin(String user, String password){
		int admin = 1;
		boolean is = false;
		//Query database for username and password
		String query = "SELECT * FROM info WHERE USER_NAME = ? AND PASSWORD = ?";
		//execute query
		try {

			//hash password

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user);
			ps.setInt(2, password.hashCode());


			results = ps.executeQuery();


			//if the user is an admin return true
			results.next();
			//is the user an admin?
			if(admin == results.getInt("ADMIN")){
				//yes
				is = true;
			}

		}catch (Exception e) {
			e.printStackTrace();
			is = false; 
			//do not grant admin priveleges
		}

		//return admin status
		return is;



	}


	/**
	 * allows the user to login
	 * returns true if successful
	 * returns false if unsuccesful
	 * 
	 * @param user
	 * @param pswd
	 * @return
	 */
	public boolean login(String user, String pswd){

		boolean success = false;
		//Query database for username and password
		String query = "SELECT USER_NAME, PASSWORD FROM info WHERE USER_NAME = ? AND PASSWORD = ?";
		//execute query
		try {

			//hash password

			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user);

			int hash = pswd.hashCode();


			ps.setInt(2, hash);


			results = ps.executeQuery();


			if(results.next()){

				success = true;

			}


		}catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		//if unssuccessful return false
		return success;

	}

	public  UserAccount getInfo(String user){
		//Query database for username getting all of the user's info
		String query = "SELECT * FROM info WHERE USER_NAME = ?";
		//execute query


		try {

			//make a prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user);

			//execute and get results
			results = ps.executeQuery();


			//if an exception is thrown do nothing
		}catch (Exception e) {
			//do nothing, remain false
			e.printStackTrace();

		}

		UserAccount bob = new UserAccount();
		
		try {
			results.next();
			bob = new UserAccount(results.getString("F_NAME"), results.getString("L_NAME"), results.getString("USER_NAME"), results.getString("CLASS_STANDING"), results.getString("MAJOR"));


		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		return bob;


	}
}