package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Academic;

import utility.DataHelper;



/**
 * Servlet implementation class LoginServlet
 */

@WebServlet({ "/Login", "/Logout" })
public class LogInServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
	private int loginAttempts;

	/**
	 * Servlet constructor
	 */
	public LogInServlet() {
		super();
	}

	/**
	 * 
	 * Process GET requests/responses (logout)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User has clicked the logout link
		request.getSession().invalidate();
		String url = "index.jsp";
		request.setAttribute("msg", "Logged Out Succesfully");
		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);


	}

	/**
	 * Process POST requests/responses (login)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get our current session
		session = request.getSession();

		//msg is used to display a message to the user
		String msg = "Log in failed";


		url = "index.jsp";
		//get the number of logins
		if(session.getAttribute("loginAttempts") == null){
			loginAttempts = 0;
		}

		//exceeded logins
		if(loginAttempts > 2){
			msg = "Error: Number of Login Attempts Exceeded";
			url = "index.jsp";


		}
		// user doesn't exist, redirect to previous page and show error
		else{
			msg = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));


			//track login attempts (combats: brute force attacks)
			session.setAttribute("loginAttempts", loginAttempts++);
			url = "index.jsp";
		}


		//pull the fields from the form
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		

		url="index.jsp";
		DataHelper dh = new DataHelper();

		Academic user = dh.login(username, password);
		
		
		if(user.getPermissions() != 4){
			try{

				//prevent session hijacking by getting new session for user.
				session.invalidate();
				session=request.getSession(true);

				session.setAttribute("user", user);
				msg = "Welcome "+ user.getFName() + " " + user.getLName();
				url = "index.jsp";


				switch(user.getPermissions()){



				case 0:{
					//is student
					url = "student";

					break;
				}
				case 1:{
					//is a teacher
					url = "teacher";

					break;
				}
				case 2:{
					//is an admin
					url = "admin";
					break;
				}

				default:{
					//record in database is messed up

					msg = "An error in the database occured";

					break;
				}


				}

			}catch(Exception e){

				msg = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));


				//track login attempts (combats: brute force attacks)
				session.setAttribute("loginAttempts", loginAttempts++);


			}

		}else{
			msg = "Login Failed. Username and/or passphrase do not match our records.";
		}
		request.setAttribute("msg", msg);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}

