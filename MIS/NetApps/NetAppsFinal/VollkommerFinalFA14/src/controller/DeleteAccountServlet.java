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
 * Servlet implementation class DeleteServlet
 */
@WebServlet({ "/DeleteAccountServlet", "/DeleteAccount" })
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAccountServlet() {
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

		String msg = "An error occured";
		
		Academic currentUser = (Academic) request.getSession().getAttribute("user");
		DataHelper dh = new DataHelper();
		String url ="index.jsp";
		
		
		if(currentUser.getPermissions() == 2){

			url = "admin";
			
			
			String formUser = request.getParameter("user");
			Academic deleteUser = new Academic(formUser);
			if(dh.delete(deleteUser)){
				//delete the delete the user submitted by admin
				msg = "Deletion of user "+ deleteUser.getEmail()  + " was successful";
				
			
			}
			
			

		}else {
			
			if(currentUser.getPermissions() == 1){
				url = "teacher";
			}else{
				url = "student";
			}
			if(dh.delete(currentUser)){
			
				//delete current user
				msg = "Deletion of user "+ currentUser.getEmail() +" was successful";
				request.getSession().invalidate();
				url = "index.jsp";
				
			}

		}


		request.setAttribute("msg", msg);


		

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);


	}



}