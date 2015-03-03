
package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ResumeHelper;

/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/UploadResume")
@MultipartConfig            // This Annotation allows us to bring in a multipart 
// input on request and treat it somewhat like regular 
// parameters

public class UploadResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadResumeServlet() {
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
		// INPUTS:
		// Get the textbox input for name - this only works for this one because 
		//    of the @MultipartConfig annotation (see above)
		String name = request.getParameter("name");
		// Retrieve <input type="file" name="fileName">
		//  be sure to import the Part class that is in the servlet package
		Part filePart = request.getPart("file"); 
		// Retrieves the actual file from the filePart. getInputStream is a method 
		//   the Part class
		InputStream filecontent = filePart.getInputStream();  

		// PROCESS:
		// Get the path of the resumes folder for storage
		String path = getServletContext().getRealPath("/resumes");
		// Get file name from the file part, this calls a method from this 
		//   servlet class that you can find below.
		String filename = getFilename(filePart);
		// Set up the full path based on the path and the file name
		String fullPath = path + "/" + filename;
		// Call a method (below in this servlet) that will write the file
		//  to the production version of the resumes folder.
		copyStream(filecontent, fullPath);
		
		//System.out.println(path);
		// OUTPUT:
		// Add path and filename to request attributes
		ResumeHelper rh = new ResumeHelper();
		rh.addResume(fullPath, name, (String)request.getSession().getAttribute("userName"));
		rh.commitResume(fullPath, (String)request.getSession().getAttribute("userName"));
		
		
		request.getSession().setAttribute("resumePath", fullPath);
		request.getSession().setAttribute("resumeName", name + ".pdf");


		// Send control to the view component
		RequestDispatcher dispatcher = request.getRequestDispatcher("/User.jsp");
		dispatcher.forward(request, response);

	}

	/*
	 * Method: getFileName
	 * Purpose: extracts file name from the file part of the Multipart input
	 */
	private String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	/*
	 * Method: copyStrem
	 * Purpose: writes an inputStream to a location and file name specified
	 *          by the fullPath.
	 */
	public static void copyStream(InputStream input, String fullPath)
			throws IOException
	{
		OutputStream output = new FileOutputStream(fullPath);
		byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1)
		{
			output.write(buffer, 0, bytesRead);
		}
		
		output.close();
	}


	// http://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet/2424824#2424824


}
