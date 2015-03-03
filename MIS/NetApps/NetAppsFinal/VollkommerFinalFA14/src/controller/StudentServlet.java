package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Academic;
import utility.DataHelper;

/**
 * Servlet implementation class AcademicServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
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

		Academic user = (Academic) request.getSession().getAttribute("user");
		String url = "index.jsp";
		String table = "You do not have permission to access this area of the site";
		if(user.getPermissions() == 0){
			//user has permission to be here
			DataHelper dh = new DataHelper();
			table = dh.studentTable(user);

			url ="student.jsp";
		}
	
		String msg = (String)request.getAttribute("msg");

		if(msg == null){
			msg ="Hey " + user.getFName() + " " + user.getLName();
		}
		

		request.setAttribute("table", table);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
