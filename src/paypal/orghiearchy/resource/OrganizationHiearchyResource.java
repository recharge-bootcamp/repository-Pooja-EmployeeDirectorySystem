package paypal.orghiearchy.resource;
	

import javax.ws.rs.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import org.xml.sax.SAXException;
import paypal.orghiearchy.service.OrganizationHiearchyService;

	
	
	@Path("/OrgHiearchy")
	public class OrganizationHiearchyResource {
		
		
		OrganizationHiearchyService orgService = new OrganizationHiearchyService();;

		/*public OrganizationHiearchyResource() {
			
		}	*/
	 
	 @POST
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON})
	 @Path("/create")
	  public StreamingOutput createEntity(@QueryParam("entity_type") int  entity_type,
			@Context HttpServletRequest request, InputStream inputstream)throws SAXException,Exception
			 { 
		 		
		 		try{
				 System.out.print(request.getParameter("entity_type"));
				 final String JsonString=orgService.createEntity(request,inputstream);
				
				 return new StreamingOutput(){
					  
					  public void write(OutputStream output)throws IOException{
							PrintWriter pw=new PrintWriter(output);
							pw.println(JsonString);
							pw.close();
				         }};
				 
				}
				catch(Exception se){
					se.printStackTrace();
					return new StreamingOutput(){
						public void write(OutputStream output)
						throws IOException{
							PrintWriter pw=new PrintWriter(output);
							pw.println("Error");
							pw.close();
				         }
					};
					
		 	     }
		 
		 	
			/* ObjectMapper mapper = new ObjectMapper();
			 mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		     final String   jsonString = mapper.writeValueAsString(OrganizationPO.class);*/
		     
	 }
	 /*entity_type:1(org), 2(vertical),3(domain),4(emp)*/
	 /* entity_id means for employee its employee_id for organization its org_id */
	 @PUT
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON})
	 @Path("/update")
	 public StreamingOutput updateEntity(@QueryParam("entity_type") int  entity_type,@QueryParam("entity_id") int  entity_id,
				@Context HttpServletRequest request,InputStream inputstream)throws SAXException,Exception{
		 
		 try{
			 System.out.print(request.getParameter("entity_type"));
			
			 final String JsonString=orgService.updateEntity(request,inputstream);
			
			 return new StreamingOutput(){
				  
				  public void write(OutputStream output)throws IOException{
						PrintWriter pw=new PrintWriter(output);
						pw.println(JsonString);
						pw.close();
			         }};
			 
			}
			catch(Exception e){
				e.printStackTrace();
				return new StreamingOutput(){
					public void write(OutputStream output)
					throws IOException{
						PrintWriter pw=new PrintWriter(output);
						pw.println("Error");
						pw.close();
			         }
				};
	 	     } 
		 
		 
	 }
	
	 @GET
	// @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON})
	 @Path("/findorgentity")
	 public StreamingOutput SelectEntity(@QueryParam("entity_type") int  entity_type,@QueryParam("entity_id") int  entity_id,
				@Context HttpServletRequest request)throws SAXException,Exception{
		 
		 try{
			 System.out.print(request.getParameter("entity_type"));
			 final String JsonString=orgService.selectEntity(request);
			
			 return new StreamingOutput(){
				  
				  public void write(OutputStream output)throws IOException{
						PrintWriter pw=new PrintWriter(output);
						pw.println(JsonString);
						pw.close();
			         }};
			 
			}
			catch(Exception e){
				e.printStackTrace();
				return new StreamingOutput(){
					public void write(OutputStream output)
					throws IOException{
						PrintWriter pw=new PrintWriter(output);
						pw.println("Error");
						pw.close();
			         }
				};
	 	     }
	 }
	 }
