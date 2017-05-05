package paypal.orghiearchy.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import org.xml.sax.SAXException;

import paypal.orghiearchy.service.OrganizationHiearchyService;
import paypal.orghiearchy.service.ReportingService;

@Path("/report")
public class ReportingResource {
	
	ReportingService reptService = new ReportingService();;


/*public ReportingResource() {
	reptService = new ReportingService();
	}	*/
	
@GET
@Produces({MediaType.APPLICATION_JSON})
 @Path("/findreport")
 public StreamingOutput SelectEntity(@QueryParam("report_type") String  report_type,@QueryParam("report_for_id") String report_for_id,
			@Context HttpServletRequest request)throws SAXException,Exception{
	 
	 try{
		 System.out.print(request.getParameter("report_type"));
		 final String JsonString=reptService.findReports( request);
		
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
