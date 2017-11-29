package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import javax.management.RuntimeErrorException;

public class ConexaoFactory {

	public static Connection getConnection(){
		// TODO Auto-generated method stub
		String driver="com.mysql.jdbc.Driver";//"com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/fabricaweb";
		String userName="root";
		String password="123456";
		
		/*try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}*/

		try {
			//System.out.println("antes...okay");
			Connection conn=DriverManager.getConnection(url, userName, password);
			//System.out.println("depois...okay");
			return conn;//DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("errro!");
			//relancando a exception como runtime
			throw new RuntimeException(e);
			//e.printStackTrace();
		}
	}

}
