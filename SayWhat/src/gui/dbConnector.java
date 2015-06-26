package gui;
import java.sql.*;

import javax.swing.JOptionPane;

public class dbConnector {
		
		static PreparedStatement stmt;	
		static Connection conn = null;
		static ResultSet result;
		static String query;
		static private String host = "jdbc:mysql://localhost:3306/saywhatdb";
		static private String pass = "";
		static private String root = "root";
		static boolean auth;
		static int count = 0;
		static int count2 = 0;
		
		public dbConnector(){
		}
		
		public static Connection getConnecttion(){
			try {
				conn = DriverManager.getConnection(host, root, pass);	
				stmt = conn.prepareStatement(query);			
			}catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the Database");
				e.printStackTrace();
			}
			return conn;
		}
		
		public static void setQuery(String query1){
			query = query1;
		}
		
		public static ResultSet getResult() throws SQLException{
			return result = stmt.executeQuery();
		}
		
		public static void closeConnection(){
			if(conn != null){
				try{
					conn.close();
					//JOptionPane.showMessageDialog(null, "Connection Closed");
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		public static boolean getAuth (String username, String password){
			authenticateUser(username, password);
			return auth;
		}

		public static boolean authenticateUser(String username, String password){
				String query = "SELECT * from usernames WHERE username=? and password=?";
				dbConnector.setQuery(query);
				dbConnector.getConnecttion();
			try{
				stmt.setString(1, username);
				stmt.setString(2, password);
				dbConnector.getResult();
				
				while (result.next()){	
				   count2++;
				}if (count2 == 1){
					auth = true;
					User currentUser = new User();
					currentUser.setLoggedIn(true);
					//----------------------------------------------------------------------------------------------->> Log User in
				}else{
					auth = false;
					JOptionPane.showMessageDialog(null, "Username and/or password is not correct, try again");
				}
				dbConnector.closeConnection();
				
			}catch(Exception e2){
				System.out.println("ERROR: Could not connect");
				JOptionPane.showMessageDialog(null, e2);
			}
			return auth;
		}
		
		public static void main(String[] args) throws SQLException{
			setQuery("SELECT * from users");
			getConnecttion();
			getResult();
			while (result.next()){	
			   System.out.println(result.getString("fname"));
			   count++;
			}
			}
}


