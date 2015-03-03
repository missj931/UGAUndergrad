package controller;


/**
 * @Matthew Vollkommer
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Amortization;
import model.Loan;

/**
 * Servlet implementation class AmortizationServlet
 */
@WebServlet(
		description = "This servlet serves as the controller for our Amortization MVC based web app", 
		urlPatterns = { "/Amortize"})
public class AmortizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmortizationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		double principal;
		double rate;
		double term;
		String theResponse;
		String url;

		Amortization amort;

		//parse doubles and create objects
		try{
			//Get data from form
			principal = Double.parseDouble(request.getParameter("principal"));
			rate = Double.parseDouble(request.getParameter("rate"));
			term = Double.parseDouble(request.getParameter("term"));


			//create new loan andamortization objects
			Loan theLoan = new Loan(principal, rate, term);
			amort = new Amortization(theLoan);
			
			
			theResponse = amort.doAmortization();
			url="/amortization.jsp";

			//an exception was thrown alert the user
		}catch(Exception e){

			url ="/error.jsp";
			theResponse = "Error";
		
		}
		
		//add values to request object to pass to the destination
		request.setAttribute("theResponse", theResponse);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	

}
