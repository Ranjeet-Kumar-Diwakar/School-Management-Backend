package com.school.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.Entity.EmailOtp;
import com.school.dto.GenerateOtpRequest;
import com.school.dto.VerifyOtpRequest;
import com.school.payload.ApiResponse;
import com.school.repository.OtpRepository;
import com.school.service.EmailServiceImpl;
import com.school.service.OtpServiceImpl;
import com.school.service.RegisterUserServiceImpl;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/public/user")
public class EmailServiceController {

	@Autowired
	private EmailServiceImpl emailService;
	@Autowired
	private OtpServiceImpl otpService;
	
	@Autowired
	RegisterUserServiceImpl studentService;
	
	@PostMapping("/send-mail")
	public ResponseEntity<ApiResponse> sendMail(@RequestBody GenerateOtpRequest request) {
		
		
		int otp = otpService.generateOtp(request.getEmail());
		
		String to = "diwakarindra@gmail.com";
		
		String subject = "Bright Future Academy - Email Verification OTP " + request.getEmail();
		
		String body = """
				Dear User,

				Your One-Time Password (OTP) for email verification is:

				%s

				This OTP is valid for 5 minutes.

				Please do not share this OTP with anyone. If you did not request this verification, you can safely ignore this email.

				Thank you,
				Bright Future Academy
				""".formatted(otp);
		
		ApiResponse response = new ApiResponse();
		System.out.println("request recive");
		
		try {
			
//			this condition is only of catch testing
//			if(true) {
//				throw new RuntimeException("Testing exep");
//			}
			emailService.sendEmail(to, subject, body);

			System.out.println("email send on " + request.getEmail());
			
			
			response.setSuccess(true);
			response.setMessage("Verification code sent successfully. Please check your email.");
			
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			
			System.out.println("catch block run");
			
			response.setSuccess(false);
			response.setMessage("Unable to connect to the server. Please check your internet");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(response);
		}
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<ApiResponse> verifyOtp(@RequestBody VerifyOtpRequest userOtpData) {
		
		System.out.println("verify otp controller " + userOtpData.getEmail());
		
		 boolean validateOtpResult = otpService.validateOtp(userOtpData.getEmail(), userOtpData.getOtp());
		 
		 System.out.println("validate otp result " + validateOtpResult);
		 ApiResponse response = new ApiResponse();
		 
		 if (validateOtpResult) {
			 response.setSuccess(true);
			 response.setMessage("Your email has been verified successfully.");
			 return ResponseEntity.ok(response);
		} else {
			response.setSuccess(false);
			response.setMessage("Invalid or expired OTP. Please try again.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(response);
		}
		 
	}
	
	


	
	
}
