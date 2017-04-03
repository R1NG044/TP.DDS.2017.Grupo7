package Dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class eliminarForeignKey {
	
	public static void main(String[] args) throws SQLException {
		
		Statement st;
		
		try {
			 
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		
		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:" + "LOCALMAR", "app_test",
					"app_test");
			connection.setAutoCommit(false);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		
		if (connection != null) {
			System.out.println("Successfully connected to Oracle DB");
		}
		else {
			System.out.println("Failed to connect to Oracle DB");
		}
		
		st = connection.createStatement();
		
		try {
			st.executeUpdate("DELETE FROM app_test.tabla_hija WHERE id = 1");
		} catch (SQLException e) {
//			e.printStackTrace();
			if (e.getErrorCode() == 2292) {
				
				System.out.println("No se puede eliminar el sector interno. La repartición correspondiente sigue activa.");
				
			} else {
			e.printStackTrace();
			}
		}
		
		st.close();
		connection.commit();
		connection.close();
		
	}
	
}
