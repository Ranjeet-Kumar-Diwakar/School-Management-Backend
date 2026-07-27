package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.LoginRequest;
import com.school.dto.LoginResponse;
import com.school.payload.ApiResponse;
import com.school.security.AuthService;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request){
		System.out.println("auth controller run " + request);
		return authService.login(request);
	}
}
