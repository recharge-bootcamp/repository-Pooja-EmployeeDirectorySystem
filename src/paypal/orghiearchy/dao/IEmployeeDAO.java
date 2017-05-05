package paypal.orghiearchy.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import paypal.orghiearchy.common.IOrgEntity;
import paypal.orghiearchy.pojo.EmployeePO;

/**
 * @author paypal
 *
 */
public interface IEmployeeDAO {
	
	public boolean createEmployee(IOrgEntity emppo) throws SQLException,Exception;;
	
	 public boolean updateEmployee(int entity_id,IOrgEntity emppo) throws SQLException,Exception;
	 
	 public ArrayList<EmployeePO> selectEmployee(int entity_id,int org_id) throws SQLException,Exception;

}