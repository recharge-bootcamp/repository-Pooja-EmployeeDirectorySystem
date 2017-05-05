package paypal.orghiearchy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

 

public class ConnectionPoolClass {
	
	
	public static Connection  getConnection() throws Exception{
		
		Connection con=null;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdir","root","SqlRoot3!");  
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return con;
	}
	
	public static Connection getReadOnlyConnection() 
			throws SQLException, NamingException, Exception
		{
	
			  Connection con =getConnection();
			  con.setReadOnly(true); 
			  return con;
		
		}

}
