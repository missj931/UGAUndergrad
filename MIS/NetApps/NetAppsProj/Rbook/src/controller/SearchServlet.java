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
 * Servlet implementation class Welcome
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// parameter

		String major = request.getParameter("major");
		String classStanding= request.getParameter("classStanding");

		if(major.equalsIgnoreCase("Select Your Major")){

			major = "";
		}

		if(classStanding.equalsIgnoreCase("Select Your Class")){
			classStanding = "";
		}



		// create a read query helper object
		DataHelper dh = new DataHelper();
		
		String path = getServletContext().getRealPath("/resumes");
		
		// Get the html table from the REadQuery object
		String table = dh.search(major, classStanding, path);

		// pass execution control to read.jsp along with the table
		request.setAttribute("table", table);
		String url = "/Browse.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

}
}