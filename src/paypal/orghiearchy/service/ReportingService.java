package paypal.orghiearchy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import paypal.orghiearchy.common.OrgPojoUtil;
import paypal.orghiearchy.dao.IRoleRept;
import paypal.orghiearchy.dao.RoleReportDAO;

public class ReportingService {
	
	
	public String findReports(HttpServletRequest request){
		String jsonString=null;
		try{
			/*org:1,Vertical:2,Domain:3,role:4*/
			String org_id=((String)request.getParameter("org_id"));
			 String report_type=((String)request.getParameter("report_type"));
			 String report_for_Id=((String)request.getParameter("report_for_id"));
			 
			 switch(report_type){  
				
				case "org": {
					
				}    
				 break;  
				case "vertical":{ 
					    
				}
				 break;  //optional  
				case "domain":{    
					 
				}
				 break;  
				case "role": {
					IRoleRept roledao=new RoleReportDAO();
					HashMap<String,List> subordinatedetail=roledao.findReptRoles(report_for_Id,org_id);
					
					jsonString=OrgPojoUtil.getPOJOToJson(subordinatedetail);
					// status=empdao.createEmployee(orgentity);
					// if(status)
						// jsonString="Succefully created.";
				}
				 break;  //optional   
				    
				default:     
				 return "Not a proper reort type ";    
				} 
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		return jsonString;
		
		
	}
	
	
	

}
