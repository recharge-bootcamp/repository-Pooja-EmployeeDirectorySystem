package paypal.orghiearchy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import paypal.orghiearchy.common.ConnectionPoolClass;
import paypal.orghiearchy.common.DBUtility;
import paypal.orghiearchy.common.IOrgEntity;
import paypal.orghiearchy.pojo.EmployeeDetailPO;
import paypal.orghiearchy.pojo.EmployeePO;

public class EmployeeDAO implements IEmployeeDAO{
	
	@Override
	public boolean createEmployee(IOrgEntity emppo)throws SQLException,Exception{
		
		boolean status=false;
		Connection con=null;
		StringBuffer empquery=new StringBuffer();
		StringBuffer empdetailquery=new StringBuffer();
		
		StringBuffer detailkey=new StringBuffer();
		StringBuffer detailvalues=new StringBuffer();
		 
		
		
		try{
		if(emppo!=null){
					
			EmployeePO employee=(EmployeePO)emppo;
			EmployeeDetailPO edetail=employee.getDetail();
			con =ConnectionPoolClass.getConnection();
			
			StringBuffer sb= new StringBuffer("Select emp_id from employee order by 1 desc");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sb.toString());
			int emp_id=0;
			if(rs.next()){
				emp_id=rs.getInt(1);
			}
			 
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			con.setAutoCommit(false);
			
			int employee_id=emp_id+1;
			
			con=ConnectionPoolClass.getConnection();
			Statement empst = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					
			con.setAutoCommit(false);
			
			empquery.append("insert into employee (emp_id,emp_title,emp_name,role_id,emp_supervisor_id, emp_status) values(");
			empquery.append(employee_id).append(",'"+employee.getEmp_title()+"',").append("'"+employee.getEmp_name()+"'").append(","+employee.getEmp_role_id())
			.append(","+employee.getEmp_supervisor_id())
			.append(","+employee.getEmp_status());
			empquery.append(")");
			System.out.println("query===="+empquery.toString());
			
			empst.executeUpdate(empquery.toString());
			
			empdetailquery.append("insert into employee_details(emp_detail_id,emp_detail_emp_id,emp_detail_org_id, emp_detail_vert_id, emp_detail_dom_id) values(");
			empdetailquery.append(employee_id).append(",").append(""+employee_id).append(","+edetail.getEmp_detail_org_id()+"").append(","+edetail.getEmp_detail_vert_id()+",").append(""+edetail.getEmp_detail_dom_id());
			empdetailquery.append(")");
			System.out.println("query===="+empdetailquery.toString());
			empst.executeUpdate(empdetailquery.toString());
			
			status=true;
			
			con.commit();
			
			DBUtility.close(con);
		}
		}
		catch(SQLException e){
			
			e.printStackTrace();
			con.rollback();
			status=false;
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			status=false;
		}
		return status;
	}
	
	@Override
	 public boolean updateEmployee(int emp_id,IOrgEntity emppo) throws SQLException,Exception{
		
		boolean status=false;
		Connection con=null;
		StringBuffer queryemployee=new StringBuffer();
		StringBuffer queryemployeedetail=new StringBuffer();
		
		
		
		
		try{
		if(emppo!=null){
					
			EmployeePO employee=(EmployeePO)emppo;
			EmployeeDetailPO edetail=employee.getDetail();
			con=ConnectionPoolClass.getConnection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					
			con.setAutoCommit(false);
			
			queryemployee.append("update employee  set emp_title='"+employee.getEmp_title()+"',emp_name='"+employee.getEmp_name()+"',role_id="+employee.getEmp_role_id()+",emp_supervisor_id="+employee.getEmp_supervisor_id()+", emp_status="+employee.getEmp_status());
			queryemployee.append(" where emp_id="+emp_id);
			System.out.println("queryemployee====="+queryemployee);
			st.executeUpdate(queryemployee.toString());
			
			queryemployeedetail.append("update employee_details set emp_detail_org_id="+edetail.getEmp_detail_org_id()+" ,emp_detail_vert_id="+edetail.getEmp_detail_vert_id()+", emp_detail_dom_id="+edetail.getEmp_detail_dom_id());
			queryemployeedetail.append(" where emp_detail_emp_id="+emp_id);
			System.out.println("queryemployee====="+queryemployeedetail);
			int i=st.executeUpdate(queryemployeedetail.toString());
			if(i>0)	
				status=true;
			con.commit();
			
			
			DBUtility.close(st);
			DBUtility.close(con);
		}
		}
		catch(SQLException e){
			
			e.printStackTrace();
			con.rollback();
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
		}
		return status;
	}
	
	@Override
	 public ArrayList<EmployeePO> selectEmployee(int entity_id,int org_id) throws SQLException,Exception{
		
		ArrayList<EmployeePO> orgentity;
		Connection con=null;
		StringBuffer queryemployee=new StringBuffer();
		orgentity=new ArrayList<EmployeePO>() ;
		
		try{
		//if(entity_id!=0)
		{
					
			
			con=ConnectionPoolClass.getReadOnlyConnection();
			Statement st = con.createStatement();
			EmployeePO emppo=new EmployeePO();
			EmployeeDetailPO empdetpo=new EmployeeDetailPO();
					
			queryemployee.append("Select emp_id,emp_title, emp_name,role_id,emp_supervisor_id, emp_status,emp_detail_id, emp_detail_emp_id ,   emp_detail_org_id, emp_detail_vert_id, emp_detail_dom_id  from employee,employee_details where emp_id=emp_detail_emp_id  ");
			if(org_id!=0)
			queryemployee.append(" and emp_detail_org_id="+org_id);
			if(entity_id!=0)
			queryemployee.append(" and emp_id="+entity_id);
			System.out.println("queryemployee===="+queryemployee);
			ResultSet rs=st.executeQuery(queryemployee.toString());
			while(rs.next()){
				 emppo=new EmployeePO();
				 empdetpo=new EmployeeDetailPO();
				emppo.setEmp_id(rs.getInt("emp_id"));
				emppo.setEmp_title(rs.getString("emp_title"));
				emppo.setEmp_name(rs.getString("emp_name"));
				emppo.setEmp_role_id(rs.getInt("role_id"));
				emppo.setEmp_supervisor_id(rs.getInt("emp_supervisor_id"));
				emppo.setEmp_status(rs.getInt("emp_status"));
				empdetpo.setEmp_detail_id(rs.getInt("emp_detail_id"));
				empdetpo.setEmp_detail_emp_id(rs.getInt("emp_detail_emp_id"));
				empdetpo.setEmp_detail_org_id(rs.getInt("emp_detail_org_id"));
				empdetpo.setEmp_detail_vert_id(rs.getInt("emp_detail_vert_id"));
				empdetpo.setEmp_detail_dom_id(rs.getInt("emp_detail_dom_id"));
				
				
				emppo.setDetail(empdetpo);	
				orgentity.add(emppo);
				
				
			}
			
			
			
			DBUtility.close(rs);
			DBUtility.close(st);
			DBUtility.close(con);
					}
		}
		catch(SQLException e){
			
			e.printStackTrace();
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		return orgentity;
	}

}