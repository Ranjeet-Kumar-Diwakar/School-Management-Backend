package com.school.payload;

public class ApiResponse {
	
	private Boolean success;
	private String message;
	
	
	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiResponse [success=" + success + ", message=" + message + "]";
	}
	
	
	
	
	
}
