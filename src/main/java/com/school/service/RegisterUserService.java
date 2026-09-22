package com.school.service;


import org.springframework.http.ResponseEntity;

import com.school.Entity.User;
import com.school.dto.SignUpRequest;
import com.school.payload.ApiResponse;

public interface RegisterUserService {
	
	public ResponseEntity<ApiResponse> registerStudent(SignUpRequest student);
}
