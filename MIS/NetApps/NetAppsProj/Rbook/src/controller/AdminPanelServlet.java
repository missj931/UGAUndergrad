package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminTools;

/**
 * Servlet implementation class AdminPanelServlet
 */
@WebServlet("/AdminPanel")
public class AdminPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPanelServlet() {
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


		AdminTools at = new AdminTools();

		
		String path = getServletContext().getRealPath("/resumes");
		String table = at.searchABC(path);

		table = at.createPage(table);

		request.setAttribute("table", table);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
		dispatcher.forward(request, response);



	}

}
