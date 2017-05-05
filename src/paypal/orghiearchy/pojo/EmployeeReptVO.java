package paypal.orghiearchy.pojo;

public class EmployeeReptVO implements IRept {

	private String role_name;
	private String emp_title;
	private String emp_name;
	private String mgr_title;
	private String mgr_name;
	
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getEmp_title() {
		return emp_title;
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
	public String getMgr_title() {
		return mgr_title;
	}
	public void setMgr_title(String mgr_title) {
		this.mgr_title = mgr_title;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	
	
	
}
