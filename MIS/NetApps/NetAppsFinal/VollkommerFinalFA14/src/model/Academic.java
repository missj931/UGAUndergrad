package model;


public class Academic {

	//class variables
	private String lName;
	private String fName;
	private Course[] courses;
	private String email;
	private int permissions;
	//**************************************************
	//constructors
	/**
	 * defualt constructor
	 */
	public Academic(){
		lName ="";
		fName ="";
		email ="";

	}

	/**
	 * constructor
	 * @param email
	 */
	public Academic(String email){
		this.email = email;
		lName ="0";
		fName ="0";
		permissions = 4;

	}

	/**
	 * constructor
	 * @param fName
	 * @param lName
	 * @param email
	 */
	public Academic(String fName, String lName, String email){
		this.lName = lName;
		this.fName = fName;
		this.email = email;
	}

	/**
	 * constructor
	 * @param fName
	 * @param lName
	 * @param email
	 * @param courses
	 */
	public Academic(String fName, String lName, String email, Course [] courses){
		this.lName = lName;
		this.fName = fName;
		this.email = email;
		this.courses = new Course [courses.length];
		for(int counter = 0; counter <= courses.length; counter++){
			this.courses [counter] = courses [counter];
		}
	}
	
	/**
	 * constructor
	 * @param fName
	 * @param lName
	 * @param email
	 * @param courses
	 * @param permissions
	 */
	public Academic(String fName, String lName, String email, Course [] courses, int permissions){
		this.lName = lName;
		this.fName = fName;
		this.email = email;
		this.courses = new Course [courses.length];
		for(int counter = 0; counter <= courses.length; counter++){
			this.courses [counter] = courses [counter];
		}
		this.permissions = permissions;
	}

	/**
	 * constructor
	 * @param fName
	 * @param lName
	 * @param email
	 * @param permissions
	 */
	public Academic(String fName, String lName, String email,  int permissions){
		this.lName = lName;
		this.fName = fName;
		this.email = email;
		this.permissions = permissions;
	}
	//end constructors
	//***************************************************************

	//************************************************************
	//methods

	//**********************************************
	//getters
	/**
	 * gets the person's first name
	 * @return
	 */
	public String getFName(){
		return fName;
	}

	/**
	 * get's the person's last name
	 * @return
	 */

	public String getLName(){
		return lName;
	}

	/**
	 * get teh person's email
	 * @return
	 */
	public String getEmail(){
		return email;

	}
	/**
	 * gets the person's permission levels
	 * @return
	 */
	public int getPermissions(){
		return permissions;
	}

	/**
	 * get's the courses the person is enrolled in
	 * @return
	 */
	public Course[] getCourses(){

		Course courses[] = new Course[this.courses.length];
		for(int counter = 0; counter <= courses.length; counter++){
			courses [counter] = this.courses [counter];
		}
		return courses;

	}

	//**************************************
	//setters
	/**
	 * sets the person's first name
	 * @param fName
	 */
	public void setFName(String fName){
		this.fName = fName;

	}

	/**
	 * set's the person's last name
	 * @param lName
	 */
	public void setLName(String lName){
		this.lName = lName;
	}

	/**
	 * set's the person's email
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}


	/**
	 * set's the person's permission levels
	 * @param permissionLevel
	 */
	public void setPermissions(int permissionLevel){
		permissions = permissionLevel;
	}


	/**
	 * set the courses the person is enrolled in
	 * @param courses
	 */
	public void setCourses(Course [] courses){
		for(int counter = 0; counter <= courses.length; counter++){
			this.courses [counter] = courses [counter];
		}
	}
	//end Methods
	//*******************************************************************

}//class
