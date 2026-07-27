package com.school.dto;

import com.school.payload.ApiResponse;

public class LoginResponse {
	
	private String token;
	private boolean success;
	private String message;
	
	
	
	public LoginResponse(Boolean success, String message, String token) {
		super();
		this.token = token;
		this.success = success;
		this.message= message;
	}
	
	
	
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getToken() {
		return token;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
