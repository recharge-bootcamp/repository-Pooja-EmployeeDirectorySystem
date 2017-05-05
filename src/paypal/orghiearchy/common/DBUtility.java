package paypal.orghiearchy.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBUtility {
	
	public boolean updateWithWhere(String tableName, String[] columns, Object[] values, Object[] where, Connection conn) throws Exception {
		
		Statement st=null;
		StringBuffer query=new StringBuffer();
		try{
		if(columns.length != values.length) {
			throw new Exception();
		}
		if(conn!=null)
		{
			ConnectionPoolClass.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}	
		
		for (int i = 0; i < columns.length; i++) {
			query.append( "update "+ tableName + " set " + columns[i] + " = " + values[i] + " where " + where[0] + " = "+where[1]);
		}
		
		if(st!=null)
			st.close();
		}
		
		catch(SQLException se){
			se.printStackTrace();
			throw se;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		return true;
	}
	
/*public boolean update(String query,  Connection conn) throws Exception {
		
		Statement st=null;
		StringBuffer query=new StringBuffer();
		try{
		
		if(conn!=null)
		{
			ConnectionPoolClass.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}	
		
			st.executeUpdate(sql);
		
		if(st!=null)
			st.close();
		}
		
		catch(SQLException se){
			se.printStackTrace();
			throw se;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		return true;
	}
	*/
	public static boolean insert(String tablename,StringBuffer keys,StringBuffer values,Connection con) throws Exception{
		Statement stmt=null;
		StringBuffer sql=new StringBuffer();
		int i=0;
		boolean flag=false;
		try{
			sql.append("insert into "+tablename+" (" +keys);
			sql.append(") values("+values);
			sql.append(")");
			stmt=con.createStatement();
			i=stmt.executeUpdate(sql.toString());
			if(i>0)
				flag=true;
		}
		catch (Exception e) {
			throw e;
		}
		finally{
			close(stmt);
		}
		return flag;
	}
	
	/**
     * Close a <code>Connection</code>, avoid closing if null.
     *
     * @param conn Connection to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    /**
     * Close a <code>ResultSet</code>, avoid closing if null.
     *
     * @param rs ResultSet to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Close a <code>Statement</code>, avoid closing if null.
     *
     * @param stmt Statement to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
    

}
