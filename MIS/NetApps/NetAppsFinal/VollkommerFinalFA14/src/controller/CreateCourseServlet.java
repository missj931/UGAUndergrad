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
 * Servlet implementation class CreateCourseServlet
 */
@WebServlet("/CreateCourse")
public class CreateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCourseServlet() {
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
		Course crs = new Course(Integer.parseInt(request.getParameter("crn")), request.getParameter("name").toUpperCase());
		
		DataHelper dh = new DataHelper();
		String msg = "Failed to create Course";
		if(dh.create(crs)){
			msg = "Created course Succesfully for CRN: "+ crs.getCRN();
		}
		
		request.setAttribute("msg",msg);
		
		String url = "admin";
		
		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
		
	}

}
