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
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		String msg = (String) request.getAttribute("msg");
		DataHelper dh = new DataHelper();
		
		if(msg == null){
			msg ="Hey";
		}
		
		
		String table ="";
		
		if(user.getPermissions() == 2){
			//user has permission to be here


			table = dh.adminTable();

			url ="admin.jsp";
		}else{
			//user does not have permission
			msg = "You do not have permission to access this area of the site";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("table", table);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
