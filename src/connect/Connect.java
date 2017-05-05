package connect;
import java.sql.*;

public class Connect {

	
	

	
		
		public static void main (String args[]){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/empdir","root","SqlRoot3!");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from organization");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));  
			  
			}
		catch(Exception e){ System.out.println(e);
		e.printStackTrace();
		
		} }
			

	
	
}
