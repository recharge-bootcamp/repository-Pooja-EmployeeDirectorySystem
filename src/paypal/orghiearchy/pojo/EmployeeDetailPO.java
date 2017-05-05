package paypal.orghiearchy.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeDetailPO {
	
	/*<emp>
<emp_title>Mrs</emp_title>
<emp_name>Kalyani</emp_name>
<emp_role_id>4</emp_role_id>
<emp_reporting_id>4</emp_reporting_id>
<emp_status>1</emp_status>
<detail>
	<emp_vert_id>1</emp_vert_id>
	<emp_domain_id>2</emp_domain_id>
	<emp_org_id>3</emp_org_id>
</detail>


</emp>
	 * 
	 * 
	 * */
	@JsonProperty("detail_id")
	int emp_detail_id ;
	@JsonProperty("emp_id")
	int emp_detail_emp_id  ; 
	@JsonProperty("emp_org_id")
	int emp_detail_org_id; 
	@JsonProperty("emp_vert_id")
	int emp_detail_vert_id;
	@JsonProperty("emp_domain_id")
	int emp_detail_dom_id;
	
	
	public int getEmp_detail_id() {
		return emp_detail_id;
	}
	public void setEmp_detail_id(int emp_detail_id) {
		this.emp_detail_id = emp_detail_id;
	}
	
	public int getEmp_detail_emp_id() {
		return emp_detail_emp_id;
	}
	
	public void setEmp_detail_emp_id(int emp_detail_emp_id) {
		this.emp_detail_emp_id = emp_detail_emp_id;
	}
	public int getEmp_detail_org_id() {
		return emp_detail_org_id;
	}
	public void setEmp_detail_org_id(int emp_detail_org_id) {
		this.emp_detail_org_id = emp_detail_org_id;
	}
	public int getEmp_detail_vert_id() {
		return emp_detail_vert_id;
	}
	public void setEmp_detail_vert_id(int emp_detail_vert_id) {
		this.emp_detail_vert_id = emp_detail_vert_id;
	}
	public int getEmp_detail_dom_id() {
		return emp_detail_dom_id;
	}
	public void setEmp_detail_dom_id(int emp_detail_dom_id) {
		this.emp_detail_dom_id = emp_detail_dom_id;
	}

}
