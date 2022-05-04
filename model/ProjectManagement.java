package model;

import java.io.Serializable;

public class ProjectManagement implements Serializable {
	
	private static final long serialVersionUID = -1842539813517800786L;


	private static int count = 1;
	
	private String project_name;
	private String location;
	private String client_name;
	private String contact_number;
	private String client_email;
	private String date_expected;
	private String date_submitted;
	private String date_signed;
	private String status;
	private String product;
	private Double value;
	private String description;
	
	public ProjectManagement(String pname, String location, String client_name, String contact_number, String client_email, String date_expected,
			 String date_submitted, String date_signed, String product, Double value, String status, String description) {
		
		this.project_name = pname;
		this.location = location;
		this.client_name = client_name;
		this.contact_number = contact_number;
		this.client_email = client_email;
		this.date_expected = date_expected;
		this.date_submitted = date_submitted;
		this.date_signed = date_signed;
		this.status = status;
		this.value = value;
		this.product = product;
		this.description = description;
		
		
		count++;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		ProjectManagement.count = count;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public String getDate_expected() {
		return date_expected;
	}

	public void setDate_expected(String date_expected) {
		this.date_expected = date_expected;
	}

	public String getDate_submitted() {
		return date_submitted;
	}

	public void setDate_submitted(String date_submitted) {
		this.date_submitted = date_submitted;
	}

	public String getDate_signed() {
		return date_signed;
	}

	public void setDate_signed(String date_signed) {
		this.date_signed = date_signed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectManagement [project_name=" + project_name + ", location=" + location + ", client_name="
				+ client_name + ", contact_number=" + contact_number + ", client_email=" + client_email
				+ ", date_expected=" + date_expected + ", date_submitted=" + date_submitted + ", date_signed="
				+ date_signed + ", status=" + status + ", product=" + product + ", value=" + value + ", description="
				+ description + "]";
	}

	
}
