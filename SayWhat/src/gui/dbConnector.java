package gui;
import java.sql.*;
import javax.swing.JOptionPane;

public class dbConnector {
		
		static PreparedStatement stmt;	
		static Connection conn = null;
		static ResultSet result;
		static String query;
		static String host;
		static String pass;
		static String root;
		static boolean auth;
		
		public dbConnector(){
			host = "jdbc:mysql://localhost:3306/saywhatdb";
			pass = "";
			root = "root";
		}
		
		public static void getConnecttion(){
					
			try {
				conn = DriverManager.getConnection(host, root, pass);	
				stmt = conn.prepareStatement(query);			
			}catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the Database");
				e.printStackTrace();
			}
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
					JOptionPane.showMessageDialog(null, "Connection Closed");
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		public static boolean getAuth(){
			authenticateUser();
			return auth;
		}
		@SuppressWarnings("deprecation")
		public static void authenticateUser(){
			
				String query = "SELECT * from usernames WHERE username=? and password=?";
				dbConnector.setQuery(query);
				dbConnector.getConnecttion();
				String username = Login.usernameField.getText();
				String password = Login.passwordField.getText();
			try{
				stmt.setString(1, username);
				stmt.setString(2, password);
				dbConnector.getResult();
				
				int count = 0;
				while (result.next()){	
				   count++;
				}if (count == 1){
					auth = true;
					User currentUser = new User();
					currentUser.setLoggedIn(true);
					//----------------------------------------------------------------------------------------------->> Log User in
				}else{
					JOptionPane.showMessageDialog(null, "Username and/or password is not correct, try again");
				}
				dbConnector.closeConnection();
				
			}catch(Exception e2){
				System.out.println("ERROR: Could not connect");
				JOptionPane.showMessageDialog(null, e2);
			}
		}
}

