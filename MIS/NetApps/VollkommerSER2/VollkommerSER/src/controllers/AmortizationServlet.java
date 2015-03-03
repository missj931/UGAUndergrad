//package
package controllers;

//imports
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Loan;
import model.Amortization;
/**
 * Servlet implementation class AmortizationServlet
 */
@WebServlet(description = "A servlet that will act as the controller for our amortization net app", urlPatterns = { "/Amortize" })
public class AmortizationServlet extends HttpServlet {

	//constant
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//*********************************************************
		//send output to the response
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		//TRY CATCH BLOCK
		try{
			// TODO Auto-generated method stub
			//get the input values from the request and convert
			Loan loan = new Loan(Double.parseDouble(request.getParameter("principal")), Double.parseDouble(request.getParameter("rate")), Double.parseDouble(request.getParameter("term")));
			Amortization amort = new Amortization(loan);

			//amortize
			String view = amort.doAmortization();


			//out.println("<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>");

			out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
			out.println("<html>");

			//link to css style sheet
			out.println("<link rel='stylesheet' type='text/css' href='Style.css'>");
			out.println("<head>");

			out.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");

			out.println("<title>Amortization</title>");

			out.println("<h1>Vollkommer's Loan Amortization Application - Table(with Servlet only)</h1>");

			out.println("</head>");


			out.println("<body>");

			//output the information
			out.println(view);

			out.println("</form>");
			out.println("<body>");

			out.println("</body>");
			out.println("</html>");

		}catch(Exception e){

			//if any exception is caught
			out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
			out.println("<html>");

			//link to css style sheet
			out.println("<link rel='stylesheet' type='text/css' href='Style.css'>");
			out.println("<head>");

			out.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");

			out.println("<title>Amortization Error</title>");

			out.println("<h1>Vollkommer's Loan Amortization Application - Table(with Servlet only)</h1>");

			out.println("</head>");


			out.println("<body>");

			//inform user an error has occured.
			out.println("<p>An Error Has occured. Make sure appropriate values are entered. </p>");

			out.println("</form>");
			out.println("<body>");

			out.println("</body>");
			out.println("</html>");
		}
		//*******************************************
	}

}
