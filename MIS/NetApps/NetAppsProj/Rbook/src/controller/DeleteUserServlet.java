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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet({ "/DeleteUserServlet", "/DeleteUser" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
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
		
		DataHelper dh = new DataHelper();
		String url = "User.jsp";
		String msg = "Failed to delete account";
		
		//did the account successfully get deleted?
		if(dh.deleteUser((String) request.getSession().getAttribute("userName"))){
			//yes
			url = "Index.jsp";
			msg ="Succesfully deleted account";
			request.getSession().invalidate();

		}
		

		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
