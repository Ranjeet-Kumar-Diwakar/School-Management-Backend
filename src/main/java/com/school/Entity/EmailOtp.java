package com.school.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailOtp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private int otp;
	private LocalDateTime generateAt;
	private LocalDateTime expiryAt;
	private boolean verified;
	
	
	public EmailOtp(Long id, String email, int otp, LocalDateTime generateAt, LocalDateTime expiryAt,
			boolean verified) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.generateAt = generateAt;
		this.expiryAt = expiryAt;
		this.verified = verified;
	}


	public EmailOtp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}


	public int  getOtp() {
		return otp;
	}


	public LocalDateTime getGenerateAt() {
		return generateAt;
	}


	public LocalDateTime getExpiryAt() {
		return expiryAt;
	}


	public boolean isVerified() {
		return verified;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}


	public void setGenerateAt(LocalDateTime generateAt) {
		this.generateAt = generateAt;
	}


	public void setExpiryAt(LocalDateTime expiryAt) {
		this.expiryAt = expiryAt;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}


	@Override
	public String toString() {
		return "GenerateOtp [id=" + id + ", email=" + email + ", otp=" + otp + ", generateAt=" + generateAt
				+ ", expiryAt=" + expiryAt + ", verified=" + verified + "]";
	}
	
	
}
