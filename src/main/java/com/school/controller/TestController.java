package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.payload.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class TestController {
	
	
	@GetMapping("/show")
	public ResponseEntity<ApiResponse> printNameAndRole() {
		
		System.out.println("test api running.....");
		
		ApiResponse response = new ApiResponse();
		
		response.setSuccess(true);
		response.setMessage("my name is ranjeet and i am a software engineer");
		
		return ResponseEntity.ok(response);
		
	}
}
