package com.school.dto;

import java.util.Arrays;
import java.util.Set;

import com.school.enums.Permission;
import com.school.payload.ApiResponse;

public class LoginResponse {
	
	private String token;
	private boolean success;
	private String message;
	private Set<Permission> permission;
	
	
	
	public LoginResponse(Boolean success, String message, String token, Set<Permission> permission) {
		super();
		this.token = token;
		this.success = success;
		this.message= message;
		this.permission = permission;
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


	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}



	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", success=" + success + ", message=" + message + ", permission="
				+ permission + "]";
	}

}
