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
 * Servlet implementation class EditAccount
 */
@WebServlet(
		name = "EditAccountServlet", 
		urlPatterns = { 
				"/EditAccount", 

		})
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAccount() {
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

		String url = "index.jsp";
		String msg = "Update Failed";
		DataHelper dh = new DataHelper();
		Academic user = new Academic(request.getParameter("first"), request.getParameter("last"), ((Academic)request.getSession().getAttribute("user")).getEmail(), ((Academic)request.getSession().getAttribute("user")).getPermissions());
		String email = request.getParameter("email");
		switch(user.getPermissions()){

		case 0:{
			url = "student";
			if(dh.update(user, email)){
				msg ="Successfully updated Account";

			}

			break;
		}
		case 1:{

			url = "teacher";
			if(dh.update(user, email)){
				msg ="Successfully updated Account";

			}


			break;
		}
		case 2:{

			url = "admin";			
			if(dh.update(user, email)){
				msg ="Successfully updated Account";
			}
			break;
		}


		}//end switch

		//get the current user's new info from the databasea
		user = dh.getAcademic(((Academic) request.getSession().getAttribute("user")).getEmail());
		request.getSession().setAttribute("user", user);
	

		//update user info on session
		request.setAttribute("msg", msg);

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);


	}

}
