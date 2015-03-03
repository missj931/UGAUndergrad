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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/AdminDeleteUser")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteUserServlet() {
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

		
		String userName =  (String) request.getParameter("userName");
		//TODO delete user
		DataHelper dh = new DataHelper();
		String msg = "User Account: " + userName + "successfully deleted";
		boolean success = dh.deleteUser(userName);
		
		if(!success){
			msg = "User Account: " + userName + "not deleted";
		}

		

		request.setAttribute("msg", msg);
		//pass control to index.jsp
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel");
		dispatcher.forward(request, response);

	}



}