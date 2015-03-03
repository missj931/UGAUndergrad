package model;

public class Course {
	private int crn;
	private Academic teacher;
	private Academic [] students;
	private String name;

	//*********************************************
	//constructors
	/**
	 * defualt constructor
	 */
	public Course(){
		crn = 0;
		name = "TBA";
	}
	
	/**
	 * constructor
	 * @param crn
	 * @param name
	 */
	public Course(int crn, String name){
		this.crn = crn;
		this.name = name;
	}

	
	/**
	 * constructor
	 * @param crn
	 */
	public Course(int crn){
		this.crn = crn;

	}
	/**
	 * constructor
	 * @param crn
	 * @param teacher
	 */
	public Course(int crn, Academic teacher){
		this.crn = crn;
		this.teacher = teacher;

	}
	/**
	 * constructor
	 * @param crn
	 * @param teacher
	 * @param students
	 */
	public Course(int crn, Academic teacher, Academic [] students){
		this.crn = crn;
		this.teacher = teacher;
		this.students = new Academic [students.length];
		for(int counter = 0; counter <= students.length; counter++){
			this.students [counter] = students [counter];
		}
	}

	//End constructors
	//********************************************************************

	//*****************************************************
	//Mthods

	//********************
	//getters

	/**
	 * retrieves the course CRN as an in
	 * @return
	 */
	public int getCRN(){

		return crn;
	}

	/**
	 * retieves the teacher as an Academic object
	 * @return
	 */
	public Academic getTeacher(){
		return teacher;

	}

	/**
	 * gets the students enrolled in the course
	 * @return an array of students
	 */
	public Academic[] getStudents(){
		students = new Academic [this.students.length];
		for(int counter = 0; counter <= this.students.length; counter++){
			students [counter] = this.students [counter];
		}

		return students;
	}


	/**
	 * gets the course name
	 * @return
	 */
	public String getName(){
		return name;
	}

	//*******************************************
	//setters
	/**
	 * sets the teacher
	 * @param teacher, teaches the course
	 */
	public void setTeacher(Academic teacher){
		this.teacher = teacher;
	}
	/**
	 * sets teh CRN for the course
	 * @param crn
	 */
	public void setCRN(int crn){
		this.crn = crn;
	}

	/**
	 * sets the students enrolled in a course
	 * @param students, an array of students
	 */
	public void setStudents(Academic [] students){
		this.students = new Academic [students.length];
		for(int counter = 0; counter <= students.length; counter++){
			this.students [counter] = students [counter];
		}
	}

	/**
	 * sets the course name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	//methods
	//**********************************************************

}//class
