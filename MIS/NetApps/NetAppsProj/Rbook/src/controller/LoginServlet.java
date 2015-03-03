package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LogInOut;
import model.ResumeHelper;
import model.UserAccount;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet(description = "A controller for handling user logins", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
	private int loginAttempts;

	/**
	 * Servlet constructor
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * 
	 * Process GET requests/responses (logout)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User has clicked the logout link
		request.getSession().invalidate();
		String url = "Index.jsp";

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

		String msg;

	
		url = "Index.jsp";
		//get the number of logins
		if(session.getAttribute("loginAttempts") == null){
			loginAttempts = 0;
		}

		//exceeded logins
		if(loginAttempts > 2){
			String errorMessage = "Error: Number of Login Attempts Exceeded";
			request.setAttribute("msg", errorMessage);
			url = "Index.jsp";


		}
		// user doesn't exist, redirect to previous page and show error
		else{
			String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
			request.setAttribute("errorMessage", errorMessage);

			//track login attempts (combats: brute force attacks)
			session.setAttribute("loginAttempts", loginAttempts++);
			url = "Index.jsp";
		}


		//pull the fields from the form
		String username = request.getParameter("userName");
		String password = request.getParameter("password");


		//invalidate current session, then get new session for our user (combats: session hijacking)
		session.invalidate();
		session=request.getSession(true);
		url="Index.jsp";

		ResumeHelper rh = new ResumeHelper();
		//ad our stuff to session attribute
		LogInOut lilo = new LogInOut();
		if(lilo.login(username, password)){


			UserAccount bob = new UserAccount();
			bob = lilo.getInfo(username);

			try{

				//prevent session hijacking by getting new session for user.
				session.invalidate();
				session=request.getSession(true);
				
				//Set sesson attributes
				String resumeName = rh.getResumeName(username) + ".pdf";
				session.setAttribute("resumeName", resumeName);
				session.setAttribute("userName", bob.getUsername());
				session.setAttribute("fname", bob.getFname());
				session.setAttribute("lname", bob.getLname());
				session.setAttribute("classStanding", bob.getClassStanding());
				session.setAttribute("major", bob.getMajor());
				
				session.setAttribute("resumeName", rh.getResumeName(bob.getUsername()));
					

				url = "User.jsp";
				msg = "Successfully Logged In.";
				if(lilo.isAdmin(username, password)){
					url = "AdminPanel";
					msg += " Welcome Admin";
					session.setAttribute("isAdmin", "1");
				}else{
					session.setAttribute("isAdmin", "0");
				}

				request.setAttribute("msg", msg);

			}catch(Exception e){
				String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
				request.setAttribute("errorMessage", errorMessage);
				e.printStackTrace();

				//track login attempts (combats: brute force attacks)
				session.setAttribute("loginAttempts", loginAttempts++);




			}
		}else{
			msg = "Log in failed";
			request.setAttribute("msg", msg);


		}


		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}


