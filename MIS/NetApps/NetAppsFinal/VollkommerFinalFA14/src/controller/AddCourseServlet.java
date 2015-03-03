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
 * Servlet implementation class AddCourseServlet
 */
@WebServlet({ "/AddCourseServlet", "/add", "/EnrollCourse" })
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
    	//initialize local variabels
    	DataHelper dh = new DataHelper();
    	Academic user = (Academic)request.getSession().getAttribute("user");
    	Course crs = new Course (Integer.parseInt(request.getParameter("crn")));
    	String msg = "An error occured";
    	String url ="index.jsp";



    	switch(user.getPermissions()){

    	case 0:{
    		//the user is a student
    		url = "student";
    		if(dh.addStudent(user, crs)){
    			msg = "Successfully added course "+ crs.getCRN();
    		}

    		break;
    	}
    	case 1:{
    		//the user is a teacher
    		url = "teacher";
    		if(dh.addTeacher(user, crs)){
    			msg = "Successfully added course "+ crs.getCRN();
    		}

    		break;
    	}
    	case 2:{
    		user = new Academic(request.getParameter("user"));
    		url ="admin";
    		switch(user.getPermissions()){
    		case 0:{
    			if(dh.addStudent(user, crs)){
    				msg = "Successfully added course "+ crs.getCRN() +" for "+ user.getFName() + " "+ user.getLName();
    			}
    			break;
    		}
    		case 1:{
    			if(dh.addTeacher(user, crs)){
    				msg = "Successfully added course "+ crs.getCRN() +" for "+ user.getFName() + " "+ user.getLName();	
    			}
    			break;
    		}
    		default:{
    			msg ="there is an error with the user's permissions";

    		}
    		}//end inner switch


    		break;

    	}
    	default:{
    		url ="index.jsp";
    		msg="there is something wrong with your account permissions";
    		break;
    	}


    	}//end switch

    	request.setAttribute("msg", msg);
    	//forward our request along
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);

		}
	}

