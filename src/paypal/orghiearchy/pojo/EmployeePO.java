package paypal.orghiearchy.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import paypal.orghiearchy.common.IOrgEntity;


public class EmployeePO implements IOrgEntity{
	
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
	
	private int emp_id;
	private String emp_title; 
	private String emp_name ;
	private int emp_role_id;
	@JsonProperty("emp_reporting_id")
	private int emp_supervisor_id;
	private int emp_status; 
	private EmployeeDetailPO detail;
	
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_title() {
		return emp_title;
	}
	public int getEmp_role_id() {
		return emp_role_id;
	}
	public void setEmp_role_id(int emp_role_id) {
		this.emp_role_id = emp_role_id;
	}
	public void setEmp_title(String emp_title) {
		this.emp_title = emp_title;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	public int getEmp_supervisor_id() {
		return emp_supervisor_id;
	}
	public void setEmp_supervisor_id(int emp_supervisor_id) {
		this.emp_supervisor_id = emp_supervisor_id;
	}
	public int getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(int emp_status) {
		this.emp_status = emp_status;
	}
	public EmployeeDetailPO getDetail() {
		return detail;
	}
	public void setDetail(EmployeeDetailPO detail) {
		this.detail = detail;
	}

}
