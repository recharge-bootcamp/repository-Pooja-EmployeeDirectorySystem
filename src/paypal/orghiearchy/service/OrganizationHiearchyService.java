package paypal.orghiearchy.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;



import paypal.orghiearchy.common.IOrgEntity;
import paypal.orghiearchy.common.OrgPojoUtil;
import paypal.orghiearchy.dao.EmployeeDAO;
import paypal.orghiearchy.dao.IEmployeeDAO;
import paypal.orghiearchy.pojo.DomainPO;
import paypal.orghiearchy.pojo.EmployeePO;
import paypal.orghiearchy.pojo.OrganizationPO;
import paypal.orghiearchy.pojo.VerticalPO;

public class OrganizationHiearchyService { 
	
	
	
	
	public  String createEntity(HttpServletRequest request,InputStream inputstream){
		boolean status=false;
		String jsonString ="";
		try{
			 //System.out.print(request.getParameter("entity_type"));
			 int entity_type1=Integer.parseInt((String)request.getParameter("entity_type"));
			 BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
			 StringBuilder responseStrBuilder = new StringBuilder();
		     String inputStr;
		     while ((inputStr = streamReader.readLine()) != null)
		           responseStrBuilder.append(inputStr);

		       
		     IOrgEntity orgentity=OrgPojoUtil.getEntityPOJO(entity_type1,responseStrBuilder.toString() );
		     
		     switch(entity_type1){  
				
				case 1: {
					
				}  
				 break;  
				case 2:{ 
					    
				}
				 break;  //optional  
				case 3:{    
					 
				}
				 break;  
				case 4: {
					IEmployeeDAO empdao=new EmployeeDAO();
					 status=empdao.createEmployee(orgentity);
					 if(status)
						 jsonString="Succefully created.";
					 else{
						 jsonString="some Problem occur";
					 }
				}
				 break;  //optional   
				    
				default:     
				 return "Not a proper entity type ";    
				} 
			 			
			 //System.out.println("inputStr==="+responseStrBuilder.toString());
			 
			 
			// OrgPojoUtil.ObjectToJson(4);
			
		     
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public String updateEntity(HttpServletRequest request,InputStream inputstream){
		boolean status=false;
		String jsonString ="";
		try{
			 //System.out.print(request.getParameter("entity_type"));
			 int entity_type1=Integer.parseInt((String)request.getParameter("entity_type"));
			 int emp_id=Integer.parseInt((String)request.getParameter("entity_id"));
			
			 BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
			 StringBuilder responseStrBuilder = new StringBuilder();
		     String inputStr;
		     while ((inputStr = streamReader.readLine()) != null)
		           responseStrBuilder.append(inputStr);

		       
		     IOrgEntity orgentity=OrgPojoUtil.getEntityPOJO(entity_type1,responseStrBuilder.toString() );
		     
		     switch(entity_type1){  
				
				case 1: {
					
				}  
				 break;  
				case 2:{ 
					 
				}
				 break;  //optional  
				case 3:{    
					 
				}
				 break;  
				case 4: {
					IEmployeeDAO empdao=new EmployeeDAO();
					 status=empdao.updateEmployee(emp_id,orgentity);
					 if(status)
						 jsonString="Succefully updated.";
					 else{
						 jsonString="Some peroblem with updation.";
					 }
				}
				 break;  //optional   
				    
				default:     
				 return "Not a proper entity type ";    
				} 
			 			
			 //System.out.println("inputStr==="+responseStrBuilder.toString());
			 
			 
			// OrgPojoUtil.ObjectToJson(4);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public String selectEntity(HttpServletRequest request){
		boolean status=false;
		String jsonString ="";
		int entity_id=0;
		int entity_type1=0;
		int org_id=0;
		try{
			 //System.out.print(request.getParameter("entity_type"));
			if(request.getParameter("entity_id")!=null)
			   entity_id=Integer.parseInt((String)request.getParameter("entity_id"));
			if(request.getParameter("entity_type")!=null)
			   entity_type1=Integer.parseInt((String)request.getParameter("entity_type"));
			if(request.getParameter("org_id")!=null)
			   org_id=Integer.parseInt((String)request.getParameter("org_id"));
			     	     
		     switch(entity_type1){  
				
				case 1: {
					
				}  
				 break;  
				case 2:{ 
					 
				}
				 break;  //optional  
				case 3:{    
					 
				}
				 break;  
				case 4: {
					IEmployeeDAO empdao=new EmployeeDAO();
					 ArrayList<EmployeePO> emplist=empdao.selectEmployee(entity_id,org_id);
					 if(emplist!=null){
					 HashMap<String,List> obj=new  HashMap<String,List>();
					 obj.put("emp",emplist);
					 jsonString=OrgPojoUtil.getPOJOToJson(entity_type1,obj);
					 
					 }else{
						 jsonString="No data"; 
						 
					 }
					
						 
				}
				 break;  //optional   
				    
				default: {    
				 
				 jsonString="Some Problem occurred";
				 return "Not a proper entity type ";
				}
				 
				} 
			 			
			 //System.out.println("inputStr==="+responseStrBuilder.toString());
			 
			 
			// OrgPojoUtil.ObjectToJson(4);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jsonString;
	}
	
	
	
	

}