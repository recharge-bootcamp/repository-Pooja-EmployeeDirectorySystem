package paypal.orghiearchy.dao;


import java.util.HashMap;
import java.util.List;

public interface IRoleRept {
	
   public HashMap<String,List>  findReptRoles(String role_id,String org_id) throws Exception;

}
