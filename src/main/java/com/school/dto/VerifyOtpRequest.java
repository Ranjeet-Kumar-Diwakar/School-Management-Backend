package com.school.dto;

public class VerifyOtpRequest {
	
	private String email;
	private int otp;
	
	
	public String getEmail() {
		return email;
	}
	public int getOtp() {
		return otp;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	
}
