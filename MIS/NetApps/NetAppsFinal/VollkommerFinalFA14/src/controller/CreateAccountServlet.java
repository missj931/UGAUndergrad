package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DataHelper;
import model.Academic;

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

		Academic person = new Academic(request.getParameter("first"), request.getParameter("last"), request.getParameter("username"), Integer.parseInt(request.getParameter("pLevel")));
		DataHelper dh = new DataHelper();
		String msg ="Failed to create account.";
		if(dh.create(person, request.getParameter("pswd"))){
			msg = "Successfully created account";

		}
		String url = "admin";
		request.setAttribute("msg", msg);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
