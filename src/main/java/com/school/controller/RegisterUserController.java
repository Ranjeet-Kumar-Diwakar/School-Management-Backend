package com.school.controller;

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
import com.school.payload.ApiResponse;
import com.school.service.RegisterUserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/public/user")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserServiceImpl registerService;
	
	@PostMapping("/register-user")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
		
		try {
			System.out.println("run successfull" + user);
			
			user.setId(RegisterUserServiceImpl.generateUserId());
			
			System.out.println(user);
			
			ApiResponse response = registerService.registerStudent(user);
			
			return ResponseEntity.ok(new ApiResponse(true, "User Registered Successfully"));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiResponse(false, "User Already Registered"));
		}
		
	}
}
