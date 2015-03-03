package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ResumeHelper;

/**
 * Servlet implementation class AdminDeleteResume
 */
@WebServlet("/AdminDeleteResume")
public class AdminDeleteResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteResumeServlet() {
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

		String userName =  request.getParameter("userName");

		
		ResumeHelper rh = new ResumeHelper();
		boolean success = rh.deleteResume(userName);

		//pass execution 
		String url = "AdminPanel";
		String msg = "";

		if(success){
			msg = "Resume deleted for User: " + userName;

		}else{
			msg ="Failed to delete resume for User: "+ userName;

		}

		request.setAttribute("msg", msg);

		//dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,  response);


		
	}

}
