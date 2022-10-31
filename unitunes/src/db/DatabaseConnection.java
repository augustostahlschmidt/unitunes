package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public DatabaseConnection() {
		
	}
	
	public Connection getConnection(){
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/default", 
							"Guto", "1234");
			
		} catch (Exception e){
			System.out.println("Could not connect to database. " + e.getMessage());
			return null;
		}
		
		return connection;
	}	
}
