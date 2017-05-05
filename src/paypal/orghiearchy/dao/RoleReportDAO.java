package paypal.orghiearchy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import paypal.orghiearchy.common.ConnectionPoolClass;
import paypal.orghiearchy.common.DBUtility;
import paypal.orghiearchy.pojo.EmployeeReptVO;
import paypal.orghiearchy.pojo.IRept;



public class RoleReportDAO implements IRoleRept{
	
	public HashMap<String,List>  findReptRoles(String role_id,String org_id) throws Exception{
		
		Connection con= ConnectionPoolClass.getConnection();
		StringBuffer query=new StringBuffer();
		HashSet<Integer> hs=new HashSet<Integer>();
		ArrayList<Integer> rolelist=new ArrayList<Integer>();
		HashMap<String,List> empmap=new HashMap<String,List>();
		ArrayList<Integer> emplist=new ArrayList<Integer>();
	
		Statement st=null;
		ResultSet rs=null;
		
		ArrayList<EmployeeReptVO> subordinatelist=new ArrayList<EmployeeReptVO>();
		try{
		if(role_id!=null){
			query= new StringBuffer("select org_name from organization where org_id="+org_id);
			 st=con.createStatement();
			 rs=st.executeQuery(query.toString());
			
			if(rs.next()){
				ArrayList<String> orgname=new ArrayList<String>();
				orgname.add(rs.getString("org_name"));
				empmap.put("org",orgname );
			}
			
			DBUtility.close(rs);
			DBUtility.close(st);
					
			query= new StringBuffer("select role_name from roles ,organization org where org.org_id=role_org_id and org_id="+org_id+" and role_id="+role_id);
			 st=con.createStatement();
			 rs=st.executeQuery(query.toString());
			
			if(rs.next()){
				ArrayList<String> as=new ArrayList<String>();
				as.add(rs.getString("role_name"));
				empmap.put("role",as );
			}
			
			DBUtility.close(rs);
			DBUtility.close(st);
					
			query= new StringBuffer("select role_id from roles ,organization org where org.org_id=role_org_id and org_id="+org_id+" and  role_report_id="+role_id);
			 st=con.createStatement();
			 rs=st.executeQuery(query.toString());
			
			while(rs.next()){
				int roleid=rs.getInt(1);
				rolelist.add(roleid);
			}
			hs.addAll(rolelist);
			System.out.println("hs===="+hs);;
			
			while(rolelist!=null && rolelist.size()>0){
				rolelist=findAllReportingRoles(rolelist,org_id, con);
				hs.addAll(rolelist);
			}
			
			if(hs!=null && hs.size()>0){
				String roleIds=StringUtils.join(hs, ',');
				
				query= new StringBuffer("select emp_id from employee where role_id in ("+roleIds+")");
				 st=con.createStatement();
				 rs=st.executeQuery(query.toString());
				 while(rs.next()){
					 emplist.add(rs.getInt(1));
					}
			}
			
			if(emplist!=null && emplist.size()>0){
				String empids=StringUtils.join(emplist, ',');
				query= new StringBuffer();
				query.append("select  e1.emp_title as emp_title,e1.emp_name employee_name,e2.emp_title sup_title,e2.emp_name as sup_name ");
				query.append("from employee e1,employee e2 where e1.emp_id in("+empids +") and e2.emp_id=e1.emp_supervisor_id" );
				 st=con.createStatement();
				 rs=st.executeQuery(query.toString());
				 while(rs.next()){
					 EmployeeReptVO rept=new EmployeeReptVO();
					 rept.setEmp_title(rs.getString("emp_title"));
					 rept.setEmp_name(rs.getString("employee_name"));
					 rept.setMgr_title(rs.getString("sup_title"));
					 rept.setMgr_name(rs.getString("sup_name"));
					 subordinatelist.add(rept);
					}
				
			}
			if(subordinatelist!=null)
				empmap.put("subordinates", subordinatelist);
			    System.out.println("all role ids===="+hs);;
				DBUtility.close(rs);
				DBUtility.close(st);
			
		}	
			
					
			
		}
		catch(SQLException e){
			
			e.printStackTrace();
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		return empmap ;
		
	}
	
	public ArrayList<Integer> findAllReportingRoles(ArrayList<Integer> rolelist,String org_id,Connection con)throws SQLException,Exception{
		
		try{
		if(rolelist!=null && rolelist.size()>=0){
			
			String roles=StringUtils.join(rolelist, ',');
			rolelist=new ArrayList<Integer>();
			StringBuffer query=new StringBuffer("select role_id from roles,organization org where org.org_id=role_org_id and org_id="+org_id+" and role_report_id in ("+roles+")");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query.toString());
			while(rs.next()){
				rolelist.add(rs.getInt(1));
			}
			DBUtility.close(rs);
			DBUtility.close(st);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rolelist;
	}
	
	public ArrayList<Integer>  findAllSubordinates(ArrayList<Integer> emplist,Connection con) throws Exception{ 
		
		try{
			if(emplist!=null && emplist.size()>=0){
				
				String empids=StringUtils.join(emplist, ',');
				emplist=new ArrayList<Integer>();
				StringBuffer query=new StringBuffer("select emp_id from employee where emp_supervisor_id="+empids);
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query.toString());
				while(rs.next()){
					emplist.add(rs.getInt(1));
				}
				DBUtility.close(rs);
				DBUtility.close(st);
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return emplist;		
	}
	
	

}
