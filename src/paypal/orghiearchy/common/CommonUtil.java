package paypal.orghiearchy.common;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	
	/*static public int handleException( Exception e )
	   {
		if (e instanceof SQLException) 
	        Common.SQLExceptionPrint((SQLException)e);
		   	  System.out.println("msg   = " + e.getMessage());
		   	  System.err.println("msg   = " + e.getMessage());
		   	  e.printStackTrace();
		   	  try {
				ErrorHandler.handle(e);
				if (e instanceof BatchUpdateException){
					BatchUpdateException	buex	=	(BatchUpdateException)e;
					SQLException ex = buex.getNextException();                
				    while (ex != null) {                                       
				      System.err.println("SQL exception:");
				      System.err.println(" Message: " + ex.getMessage());
				      System.err.println(" SQLSTATE: " + ex.getSQLState());
				      System.err.println(" Error code: " + ex.getErrorCode());
				      ErrorHandler.handle(ex);
				      ex = ex.getNextException();
				    }
				}
					
			} catch (ErrorHandlerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      return( 0 );
	   }	*/
	
	
	

}
