package com.ametis.cms.dto;

public class ContactPersonDto {

	private String contactPersonId;
	private String name;
	private String email;
	private String jobPosition;
	private String telephone; 
	private String department;
	private String handphone;
	
	public String getHandphone() {
		return handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public ContactPersonDto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	
	
	
}
