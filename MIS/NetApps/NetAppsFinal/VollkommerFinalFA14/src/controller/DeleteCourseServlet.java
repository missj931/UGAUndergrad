package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DataHelper;
import model.Course;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet({ "/DeleteCourseServlet", "/DeleteCourse" })
public class DeleteCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCourseServlet() {
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

		//initialize loca variables
		String msg = "An error occured";
		int course = Integer.parseInt(request.getParameter("crn"));
		Course crs = new Course(course);
		DataHelper dh = new DataHelper();
		
		//delete course
		if(dh.delete(crs)){
			msg = "Deletion of course "+ crs.getCRN() + " was successful";
		}

		request.getSession().setAttribute("msg", msg);


		String url ="admin.jsp";

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
