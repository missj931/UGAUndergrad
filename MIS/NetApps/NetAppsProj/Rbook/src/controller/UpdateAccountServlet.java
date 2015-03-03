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
 * Servlet implementation class UpdateAccountServlet
 */
@WebServlet({ "/UpdateAccountServlet", "/UpdateAccount" })
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = (String) request.getSession().getAttribute("userName");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String admin = "0";
		
		
		String major = request.getParameter("major");
		String classStanding = request.getParameter("classStanding");

		if(lastName.equals("Last Name")){
			lastName ="";
		}
		
		if(firstName.equals("First Name")){
			firstName = "";
		}
		
		
		if(major.equalsIgnoreCase("Select Your Major")){

			major = "";
		}

		if(classStanding.equalsIgnoreCase("Select Your Class")){
			classStanding = "";
		}

		String msg = "Update Failed";
		// create a read query helper object
		DataHelper dh = new DataHelper();


		String url ="User.jsp";


		boolean success = dh.updateUser(userName, firstName, lastName, major, classStanding);



		if(success){

			HttpSession session = request.getSession();

			//set attributes from the session object
			session.setAttribute("userName", userName);

			session.setAttribute("fname", firstName); 

			session.setAttribute("lname", lastName); 

			session.setAttribute("major", major); 

			session.setAttribute("classStanding", classStanding);

			session.setAttribute("isAdmin", admin);

			msg = "Successfully updated account for User: " + userName + ", " + firstName +" " + lastName;


		}else{

			//tell the person the user account already exists

			msg = "Updating account for " + userName + " failed";


		}
		
		


		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
