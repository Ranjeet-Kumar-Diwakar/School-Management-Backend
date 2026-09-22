package com.school.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.Entity.User;
import com.school.dto.SignUpRequest;
import com.school.payload.ApiResponse;
import com.school.service.RegisterUserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/public/user")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserServiceImpl registerService;
	
	@PostMapping("/register-user")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
		System.out.println(signUpRequest + "....................");
		
		
		try {
			System.out.println("run successfull" + signUpRequest);
			
			ResponseEntity<ApiResponse> response = registerService.registerStudent(signUpRequest);
			
			return ResponseEntity.ok(new ApiResponse(true, "User Registered Successfully"));
		} catch (Exception ex) {
			System.out.println("-------------------------------");
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiResponse(false, "User Already Registered"));
		}
		
	}
}
