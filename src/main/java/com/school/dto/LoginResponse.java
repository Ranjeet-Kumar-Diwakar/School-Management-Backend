package com.school.dto;

import java.util.Arrays;
import java.util.Set;

import com.school.Entity.User;
import com.school.enums.Permission;
import com.school.payload.ApiResponse;

public class LoginResponse {
	
	private String token;
	private boolean success;
	private String message;
	private UserResponse user;
	private Set<Permission> permission;
	
	
	
	public LoginResponse(Boolean success, String message, String token, UserResponse user, Set<Permission> permission) {
		super();
		this.token = token;
		this.success = success;
		this.message= message;
		this.user = user;
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

	


	public UserResponse getUser() {
		return user;
	}



	public void setUser(UserResponse user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", success=" + success + ", message=" + message + ", user=" + user
				+ ", permission=" + permission + "]";
	}



}
