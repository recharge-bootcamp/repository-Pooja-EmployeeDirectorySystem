package paypal.orghiearchy.common;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonRootName;

import paypal.orghiearchy.pojo.DomainPO;
import paypal.orghiearchy.pojo.EmployeeDetailPO;
import paypal.orghiearchy.pojo.EmployeePO;
import paypal.orghiearchy.pojo.OrganizationPO;
import paypal.orghiearchy.pojo.VerticalPO;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;


public class OrgPojoUtil {
	
	
	// entity_type=1(org),2(vertical),3(domain) 4: Employee
	static Logger log = Logger.getLogger(OrgPojoUtil.class.getName());
	
	public static IOrgEntity getEntityPOJO(int entity_type,String jsonString)throws Exception{
		
		 ObjectMapper mapper = new ObjectMapper();
		// ArrayList<IOrgEntity> orgentity=null;
		 IOrgEntity orgentity=null;
		
		 try{
		switch(entity_type){  
			
		case 1:    
			 orgentity = mapper.readValue(jsonString, OrganizationPO.class);    
		 break;  
		case 2: 
			 orgentity = mapper.readValue(jsonString, VerticalPO.class);
		 break;  //optional  
		case 3:    
			 orgentity = mapper.readValue(jsonString, DomainPO.class);    
		 break;  
		case 4: {
			// orgentity = mapper.readValue(jsonString, ArrayList.class);
			orgentity = mapper.readValue(jsonString, EmployeePO.class); 
			 }
		 break;  //optional   
		    
		default:     
		 return null;    
		} 
		 }
		 catch(JsonMappingException je ){
			 
			 log.error(je.getStackTrace());
		 }
		 catch(JsonParseException jpe ){
			 
			 log.error(jpe.getStackTrace());
		 }
		 catch(IOException ie ){
			 
			 log.error(ie.getStackTrace());
		 }
		 catch(Exception e){
			 log.error(e.getStackTrace());
			 throw e; 
		 }
			 
		return orgentity;
		}
	
	
	public static String  getPOJOToJson(int entity_type,HashMap<String,List> Obj)throws Exception{
		
		 ObjectMapper mapper = new ObjectMapper();
		 ArrayList<EmployeePO> empobj=null;
		 String json="";
		 try{
		switch(entity_type){  
			
		case 1:    
			// orgentity = mapper.readValue(jsonString, OrganizationPO.class);    
		 break;  
		case 2: 
			// orgentity = mapper.readValue(jsonString, VerticalPO.class);
		 break;  //optional  
		case 3:    
			// orgentity = mapper.readValue(jsonString, DomainPO.class);    
		 break;  
		case 4: {
			  json = mapper.writeValueAsString(Obj);
			 System.out.println("json==="+json);
			 }
		 break;  //optional   .
		    
		default:     
		 return "Invalid type";    
		} 
		 }
		 catch(JsonMappingException je ){
			 
			 log.error(je.getStackTrace());
		 }
		 catch(JsonParseException jpe ){
			 
			 log.error(jpe.getStackTrace());
		 }
		 catch(IOException ie ){
			 
			 log.error(ie.getStackTrace());
		 }
		 catch(Exception e){
			 log.error(e.getStackTrace());
			 throw e; 
		 }			 
		return json;
		}
	
	
	
		public static String  getPOJOToJson(HashMap<String,List> Obj)throws Exception{
			
			ObjectMapper mapper = new ObjectMapper();
			 ArrayList<EmployeePO> empobj=null;
			 String json="";
			 try{
			
				  json = mapper.writeValueAsString(Obj);
				 System.out.println("json==="+json);
				
			 }
			 catch(JsonMappingException je ){
				 
				 log.error(je.getStackTrace());
			 }
			 catch(JsonParseException jpe ){
				 
				 log.error(jpe.getStackTrace());
			 }
			 catch(IOException ie ){
				 
				 log.error(ie.getStackTrace());
			 }
			 catch(Exception e){
				 log.error(e.getStackTrace());
				 throw e; 
			 }			 
			return json;
			}
	
		
		public static String ObjectToJson(int entity_type ) throws Exception{
			HashMap<String,List> obj =new HashMap<String,List>();
			String Json=null;
			
			List<EmployeePO> emplist=new ArrayList<EmployeePO>();
			List<OrganizationPO> orglist=new ArrayList<OrganizationPO>();
			
			EmployeePO empPO=new EmployeePO();
			EmployeeDetailPO empdetailPO=new EmployeeDetailPO();
			empPO.setEmp_id(1);
			empPO.setEmp_name("check");
			empPO.setEmp_role_id(2);
			empdetailPO.setEmp_detail_org_id(3);
			empdetailPO.setEmp_detail_vert_id(3);
			empdetailPO.setEmp_detail_dom_id(3);
			empPO.setDetail(empdetailPO); 
			
			EmployeePO empPO1=new EmployeePO();
			
			empPO1.setEmp_id(2);
			empPO1.setEmp_name("check");
			empPO1.setEmp_role_id(2);
			empdetailPO.setEmp_detail_org_id(3);
			empdetailPO.setEmp_detail_vert_id(3);
			empdetailPO.setEmp_detail_dom_id(3);
			empPO.setDetail(empdetailPO);
			emplist.add(empPO);
			emplist.add(empPO1);
			
			
			OrganizationPO orgObj=new OrganizationPO();
			orgObj.setOrgId(10);
			orgObj.setOrgName("Astst");
			orgObj.setOrgStatus(1);
			orglist.add(orgObj);
			obj.put("employee",emplist);
			obj.put("org",orglist);
			
			getPOJOToJson(4,obj);
			
			
			return Json; 
		}
		
		
		
	}

