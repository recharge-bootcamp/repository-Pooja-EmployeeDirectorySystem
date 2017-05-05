package paypal.orghiearchy.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import paypal.orghiearchy.common.IOrgEntity;

@Entity
@Table
public class OrganizationPO implements IOrgEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 	
   
   private int orgId;
   private String orgName;
   private int orgStatus;
   
   
   public OrganizationPO(int org_id, String org_name, int org_status) {
      super( );
      this.orgId = org_id;
      this.orgName = org_name;
      this.orgStatus = org_status;
     
   }

   public int getOrgId() {
	return orgId;
}

public void setOrgId(int orgId) {
	this.orgId = orgId;
}

public String getOrgName() {
	return orgName;
}

public void setOrgName(String orgName) {
	this.orgName = orgName;
}




public int getOrgStatus() {
	return orgStatus;
}

public void setOrgStatus(int orgStatus) {
	this.orgStatus = orgStatus;
}

public OrganizationPO( ) {
      super();
   }
  

@Override
   public String toString() {
      return "Organization [orgId=" + orgId + ", orgname=" + orgName + ", orgstatus=" + orgStatus + "]";
   }
}
