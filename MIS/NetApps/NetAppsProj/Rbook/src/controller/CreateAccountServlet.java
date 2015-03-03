package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataHelper;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountServlet() {
		super();

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

		String userName = request.getParameter("userName");;
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		boolean isAdmin = false;
		String major = request.getParameter("major");
		String classStanding = request.getParameter("classStanding");

		String password = request.getParameter("password");

		if(major.equalsIgnoreCase("Select Your Major")){

			major = "";
		}
		
		if(lastName.equals("Last Name")){
			lastName ="";
		}
		
		if(firstName.equals("First Name")){
			firstName = "";
		}
		

		if(classStanding.equalsIgnoreCase("Select Your Class")){
			classStanding = "";
		}

		String msg = "Create Account Failed";
		// create a read query helper object
		DataHelper dh = new DataHelper();


		String url ="Index.jsp";


		boolean success = dh.createUser(userName, firstName, lastName, isAdmin, major, classStanding, password);



		if(success){

			HttpSession session = request.getSession();

			//get attributes from the session object
			session.setAttribute("userName", userName); //textbox

			session.setAttribute("fname", firstName); //text box

			session.setAttribute("lname", lastName); //textbox

			session.setAttribute("major", major); //dropdown menu

			session.setAttribute("classStanding", classStanding); //list
			
			session.setAttribute("isAdmin", 0);

			msg = "Successfully created account for User: " + userName + ", " + firstName +" " + lastName;

			url = "User.jsp";

		}else{

			//tell the person the user account already exists

			msg = "User Name " + userName + " already exists, try a different UserName.";


		}


		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
