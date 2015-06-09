package gui;
import java.sql.*;

import javax.swing.JOptionPane;

public class dbConnector {
	
	public static void main(String[] args){ 
		
		Connection conn = null;
		try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/saywhatdb", "root", "");
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * from users WHERE role_id_fk = 00001");
				System.out.println("Connected to Database");
				while (result.next()){
					System.out.println(result.getString("lname") + ", " + result.getString("fname"));
				}
			}catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the Database");
				e.printStackTrace();
			}
		finally{
			try{
				if(conn != null)
					conn.close();
			}catch (SQLException se){
				se.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "Connection Closed");
	}
}
