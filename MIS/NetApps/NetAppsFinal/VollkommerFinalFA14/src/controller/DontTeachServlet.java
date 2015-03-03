package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Academic;
import model.Course;
import utility.DataHelper;

/**
 * Servlet implementation class dontTeachServlet
 */
@WebServlet({ "/DontTeachServlet", "/DontTeach" })
public class DontTeachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DontTeachServlet() {
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
		Course crs = new Course(Integer.parseInt(request.getParameter("crn")));
		DataHelper dh = new DataHelper();
		String msg = "An error occured";
		String url = "teacher";
		if(dh.dontTeach(user, crs)){
			msg = "Successfully dropped course CRN: "+ crs.getCRN();
		}

		//update user info on session
		request.setAttribute("msg", msg);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
	}


