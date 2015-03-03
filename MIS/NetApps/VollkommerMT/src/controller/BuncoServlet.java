package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.BuncoTable;

/**
 * Servlet implementation class BuncoServlet
 * @author Matthew Vollkommer
 */
@WebServlet(description = "A servlet that will act as the controller for our Bunco Net App", urlPatterns = { "/play" })
public class BuncoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuncoServlet() {
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

		///*********************************************************************************
		//get info submitted from for
		int round = Integer.parseInt(request.getParameter("round"));
		int tableOneTotal = Integer.parseInt(request.getParameter("tableOneTotal"));
		int tableTwoTotal = Integer.parseInt(request.getParameter("tableTwoTotal"));
		int roundCount = Integer.parseInt(request.getParameter("roundCount"));
		String tableOnePlayer = request.getParameter("tableOnePlayer");
		String tableTwoPlayer = request.getParameter("tableTwoPlayer");

		// debug System.out.println(roundCount);

		//************************************************************************************


		String str =

				//*************************************************************************
				//beginning of jsp page


				"<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>" +

		"<html>" +

		"<link rel='stylesheet' type='text/css' href='Style.css'>" +


		"<head>" +

		"<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>" +

		"<title>Bunco Simulation!</title>" +

		"</head>" +



		"<body>";

		//***************************************************************************************************

		//****************************************************************************************************************
		//show results view
		if(round ==7){

			str+="<h3>Bunco Simulator Results</h3>"+
					"<p>";

			//*********************************************
			//who won?
			if(tableOneTotal > tableTwoTotal){
				str+="Table One Wins";
			}else if(tableOneTotal < tableTwoTotal){
				str+="Table Two Wins";
			}else{
				str+="It's a Tie";
			}
			str+= "</p> <p>Table One Total: " + tableOneTotal +"</p>"+
					"<p>Table Two Total: " + tableTwoTotal +"</p>";
			//*****************************************

			//*******************************************************
			//Play Again Form

			str += "<form name='continueForm' action='play' method='post'>" +
					"<input type='hidden' name='round' value='" + 1+"'/>" +
					"<input type='hidden' name='tableOnePlayer' value='North'/>" +
					"<input type='hidden' name='tableTwoPlayer' value='North'/>" +
					"<input type='hidden' name='tableOneTotal' value='0'/>" +
					"<input type='hidden' name='tableTwoTotal' value='0'/>" +
					"<input type='hidden' name='round' value='1'/>"+
					"<input type='hidden' name='roundCount' value='1'/>"+
					"<input type=submit value='Play Again'>" +
					"</form>";
			//***********************************************************************
			//************************************************************************************************************

			//*****************************************************************************************************
			//Show Tables View
		}else{
			//show tables view

			str +="<h1>Bunco! - Round " + round + "</h1>";

			//************************************************************************************

			//*************************************************************
			//Head Table


			str += "<h3>Head Table</h3>";


			BuncoTable headTable = new BuncoTable();
			headTable.roll();
			str += headTable.getDieHTML(headTable.getValueOne());
			str += headTable.getDieHTML(headTable.getValueTwo());
			str += headTable.getDieHTML(headTable.getValueThree());


			//new round?
			//yes if head table gets a bunco, score of 21
			headTable.calcScore(round);
			if(headTable.getScore() == 21){
				str+="<p>Bunco- Last roll for Round " + round;

			}else{
				str+="<p>No Bunco- Round Continues";
			}

			//***********************************************************************


			//*********************************************************************************
			//TableOne

			str += "<h3>Table One</h3>";

			BuncoTable TableOne = new BuncoTable();

			TableOne.roll();
			TableOne.calcScore(round);

			str += TableOne.getDieHTML(TableOne.getValueOne());
			str += TableOne.getDieHTML(TableOne.getValueTwo());
			str += TableOne.getDieHTML(TableOne.getValueThree());

			str += TableOne.getScoreHTML();


			if(TableOne.getScore() != 0){
				str+= "Keep rolling " + request.getParameter("tableOnePlayer");
			}else{
				tableOnePlayer = TableOne.getNewPlayer(tableOnePlayer);
				str+= "New Player: " + tableOnePlayer;
			}

			tableOneTotal += TableOne.getScore();
			str += "<p>Total score: " + tableOneTotal + "</p>";
			//********************************************************************************

			//***************************************************************************************
			//TableTwo
			str += "<h3>Table Two</h3>";

			BuncoTable TableTwo = new BuncoTable();

			TableTwo.roll();
			TableTwo.calcScore(round);

			str += TableTwo.getDieHTML(TableTwo.getValueOne());
			str += TableTwo.getDieHTML(TableTwo.getValueTwo());
			str += TableTwo.getDieHTML(TableTwo.getValueThree());

			str += TableTwo.getScoreHTML();


			if(TableTwo.getScore() != 0){
				str+= "Keep rolling " + request.getParameter("tableTwoPlayer");
			}else{
				tableTwoPlayer = TableTwo.getNewPlayer(tableTwoPlayer);
				str+= "New Player: " + tableTwoPlayer;
			}

			tableTwoTotal += TableTwo.getScore();
			str += "<p>Total score: " +tableTwoTotal + "</p>";


			//****************************************************************************************


			//increment round if head table has bunco
			if(headTable.getScore() == 21){
				round++;//increase round by one
				roundCount=1;//restart round count

			}





			//**********************************************
			//What round is it?
			String st ="";


			if(roundCount == 1){
				st+= "Start Round ";
			}else{
				st+= "Continue Round ";
			}

			switch (round){
			case 1:
				st += "1";
				break;
			case 2:
				st += "2";
				break;
			case 3:
				st+="3";
				break;
			case 4:
				st+="4";
				break;
			case 5:
				st+="5";
				break;
			case 6:
				st+="6";
				break;
			case 7:
				st="View Results";
				break;

			}

			roundCount++;//always increment the round count.

			//*********************************************************************

			//******************************************************************************************
			//Continue Form
			str += "<form name='continueForm' action='play' method='post'>" +
					"<input type='hidden' name='round' value='" + round +"'/>" +
					"<input type='hidden' name='tableOnePlayer' value='" + tableOnePlayer +"'/>" +
					"<input type='hidden' name='tableTwoPlayer' value='" + tableTwoPlayer +"'/>" +
					"<input type='hidden' name='tableOneTotal' value='" +tableOneTotal + "'/>" +
					"<input type='hidden' name='tableTwoTotal' value='" +tableTwoTotal + "'/>" +
					"<input type='hidden' name='round' value='" + round + "'/>"+
					"<input type='hidden' name='roundCount' value='"+roundCount + "'/>"+
					"<input type=submit value='" + st+ "'>" +
					"</form>";
		}
		//*******************************************************************************
		//Exit Form
		str += "<form name='exitForm' action='index.jsp'>" +
				"<input type=submit value='Exit'>" +
				"</form>" +
				//*****************************************************************************************

		//end body tag
		"</body>" +

		//end html tag
		"</html>";

		//display to client
		out.println(str);

	}
}
