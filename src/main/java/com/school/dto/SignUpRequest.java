package com.school.dto;

public class SignUpRequest {
	
	private String name;
	private String email;
	private String phone;
	private String role;
	private String gender;
	private String password;
	private String confirmPassword;
	
	public SignUpRequest(String name, String email, String phone, String role, String gender, String password,
			String confirmPassword) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	
	
	public SignUpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getRole() {
		return role;
	}
	public String getGender() {
		return gender;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	@Override
	public String toString() {
		return "SignUpRequest [name=" + name + ", email=" + email + ", phone=" + phone + ", role=" + role + ", gender="
				+ gender + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
	
	
}
