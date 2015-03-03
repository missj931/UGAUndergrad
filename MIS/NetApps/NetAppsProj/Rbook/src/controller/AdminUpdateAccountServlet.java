package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataHelper;

/**
 * Servlet implementation class AdminCreateAccountServlet
 */
@WebServlet("/AdminUpdate")
public class AdminUpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	


		//get parameters from the form
		String userName = request.getParameter("userName");;
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String admin = "0";
		
		if(lastName.equals("Last Name")){
			lastName ="";
		}
		
		if(firstName.equals("First Name")){
			firstName = "";
		}
		
		
		try{
			admin = (String)request.getParameter("isAdmin");
		}catch(Exception e){
			//do nothing, admin still equal to 0;
		}
		String major = request.getParameter("major");
		String classStanding = request.getParameter("classStanding");
		boolean isAdmin = false;
		
		//handle input
		if(major.equalsIgnoreCase("Select Your Major")){

			major = "";
		}

		if(classStanding.equalsIgnoreCase("Select Your Class")){
			classStanding = "";
		}


		try{
			if(admin.equals("1")){
				isAdmin = true;
			}
		}catch (Exception e){
			isAdmin = false;
		}

		String msg = "Create Account Failed";
		// create a read query helper object
		DataHelper dh = new DataHelper();


		String url ="AdminPanel";


		boolean success = dh.updateUser(userName, firstName, lastName, isAdmin, major, classStanding);


		if(success){



			msg = "Successfully created account for User: " + userName + ", " + firstName +" " + lastName;


		}else{

			//tell the person the user account already exists

			msg = "Failed to update " + userName;


		}


		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}



}
