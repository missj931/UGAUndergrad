package model;

public class UserAccount {

	
	private String fname, lname, username, classStanding, major;
	
	public UserAccount(){
		
	}
	
	public UserAccount(String fname, String lname, String username, String classStanding, String major){
		
		this.setFname(fname);
		this.setLname(lname);
		this.setUsername(username);
		this.setClassStanding(classStanding);
		this.setMajor(major);
		
		
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getClassStanding() {
		return classStanding;
	}

	public void setClassStanding(String classStanding) {
		this.classStanding = classStanding;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
	
}
