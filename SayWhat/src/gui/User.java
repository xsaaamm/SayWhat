package gui;

public class User {

	private static User instance = null;
	private int id = 0;
	private String email = null;
	private boolean loggedIn = false;
	private String fname = null;
	private String lname = null;
	private int role = 1;
	private int status = 1;
	
	
	protected User(){
		//exist only to defeat instantiation
	}public static User getCurrentUser(){
		if(instance == null){
			instance = new User();
		}return instance;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public void logout(){
		this.setLoggedIn(false);
		this.id = 0;
		this.email = null;
		instance=null;
	}
	public int getUserID() {
		return id;
	}public void setUserID(int id) {
			this.id = id;
	}
	public String getEmail() {
		return email;
	}public void setEmail(String email) {
		this.email = email;
	}
	public String getfname() {
		return fname;
	}public void setfname(String fname) {
		this.fname = fname;
	}
	public String getlname() {
		return lname;
	}public void setlname(String lname) {
		this.fname = lname;
	}
	public int getrole() {
		return role;
	}public void setrole(int role) {
		this.id = role;
	}
	public int getstatus() {
		return status;
	}public void setstatus(int status) {
		this.id = status;
	}
	
}
