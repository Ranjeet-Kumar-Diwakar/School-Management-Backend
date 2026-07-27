package com.school.service;


import com.school.Entity.User;
import com.school.payload.ApiResponse;

public interface RegisterUserService {
	
	public ApiResponse registerStudent(User student);
}
