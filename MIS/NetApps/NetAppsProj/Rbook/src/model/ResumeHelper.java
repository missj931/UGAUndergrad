
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class ResumeHelper {


	//**********************************
	//class variables
	private ResultSet results;
	private Connection connection;
	private static final int BUFFER_SIZE = 4096;
	//end class variables
	//********************************

	//***************************************
	//Constructor

	public ResumeHelper(){


		String dbName = "resume_book";
		String uname = "root";
		String pwd = "";
		String url = "jdbc:mysql://localhost:3306/" + dbName;


		// set up the driver
		try{


			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, uname, pwd);

		}catch(Exception e){

			//do nothing

		}
	}

	//*************************************************************************

	//**************************************************************************
	//public Methods


	public boolean addResume(String path, String name, String userName){

		boolean success = true;


		String query = "UPDATE info SET RESUME_PATH = ? , RESUME_NAME = ? WHERE USER_NAME = ? ";
		try {

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, path);
			ps.setString(2, name);
			ps.setString(3, userName);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			success= false;
		}

		return success;

	}


	public boolean deleteResume(String userName){

		boolean success = true;
		String query = "UPDATE info SET RESUME_PATH = ? , RESUME_NAME = ? WHERE USER_NAME = ? ";

		try {

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, "");
			ps.setString(2, "");
			ps.setString(3, userName);
			ps.executeUpdate();
			
			deleteFile(userName);
			//

		} catch (Exception e) {
			e.printStackTrace();
			success= false;
		}

		

		return success;


	}


	public String getResumePath(String userName){

		String resume =" ";
		String query = "SELECT * FROM info WHERE USER_NAME = ?";
		try {

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, userName);

			results = ps.executeQuery();
	
			results.next();
			resume = results.getString("RESUME_PATH");
			

		} catch (Exception e) {
			
		}

		return resume;

	}

	public String getResumeName(String userName){


		//System.out.println(userName);
		String resume = "";
		String query = "SELECT * FROM info WHERE USER_NAME = ? ";
		try {

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, userName);

			results = ps.executeQuery();
			results.next();
			resume = results.getString("RESUME_NAME");
			

		} catch (Exception e) {
			resume = "";
		}

		if (resume.equals("NULL")){
			resume="";
		}
		return resume;

	}


	public boolean commitResume(String fullPath, String userName){
		boolean success = true;

		
		deleteFile(userName);
		
		try{

			String sql = "UPDATE info SET RESUME_FILE = ? WHERE USER_NAME = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			File file = new File(fullPath);
			FileInputStream   fis = new FileInputStream(file);

			stmt.setBinaryStream(1, fis, (int) file.length());


			stmt.setString(2, userName);



			stmt.execute();


			fis.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;

		}

		return success;

	}

	public void deleteFile(String user){
		
		String sql = "UPDATE info SET RESUME_FILE = NULL WHERE USER_NAME = ?";
		try {
			PreparedStatement statement;
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, user );

			statement.executeUpdate();
	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeResume(String user, String filePath){

		try {

			String sql = "SELECT RESUME_FILE FROM info WHERE USER_NAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user );


			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Blob blob = result.getBlob("RESUME_FILE");
				InputStream inputStream = blob.getBinaryStream();
				OutputStream outputStream = new FileOutputStream(filePath);

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();

			}
			connection.close();
		} catch (Exception e) {

		}
	}
}

//End publicMethods
//************************************************************************
