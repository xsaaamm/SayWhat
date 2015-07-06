package gui;

import java.sql.*;
import javax.swing.JOptionPane;

public class dbConnector {
		
	static PreparedStatement statement;	
	static Connection connection = null;
	static ResultSet result;
	static String query;
	static private String host = "jdbc:mysql://localhost:3306/saywhatdb";
	static private String pass = "";
	static private String root = "root";
	static boolean auth;
	static int count = 0;
	static int count2 = 0;
	
	//Get Connected to Database
	public static Connection getConnection(){
		try {
			connection = DriverManager.getConnection(host, root, pass);
		}catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the Database");
			e.printStackTrace();
		}return connection;
	}
	
	//Set Query to pass to database
	public static void setQuery(String query1) throws SQLException{
		query = query1;
		statement = connection.prepareStatement(query);
	}
	
	//Get Result from passes query
	public static ResultSet getResult() throws SQLException{
		return result = statement.executeQuery();
	}
	
	//Close Connection
	public static void closeConnection(){
		if(connection != null){
			try{
				connection.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
	//Return true/false for user athentication
	public static boolean getAuth (String username, String password){
		authenticateUser(username, password);
		return auth;
	}
	
	//Validates username and password from login textfields
	public static boolean authenticateUser(String username, String password){
		String query = "SELECT * from usernames WHERE username=? and password=?";
		try {
			dbConnector.setQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.getConnection();
		try{
			statement.setString(1, username);
			statement.setString(2, password);
			dbConnector.getResult();
			while (result.next()){	
			   count2++;
			}if (count2 == 1){
				auth = true;
			}else{
				auth = false;
				JOptionPane.showMessageDialog(null, "Incorrect username and/or password, please try again.");
			}
			dbConnector.closeConnection();				
		}catch(Exception e2){
			System.out.println("ERROR: Could not connect");
			JOptionPane.showMessageDialog(null, e2);
		}
		return auth;
	}
}


