package com.school.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.school.Entity.User;
import com.school.exception.UserAlreadyExistsException;
import com.school.payload.ApiResponse;
import com.school.repository.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {


	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public ApiResponse registerStudent(User user) {

		Optional<User> studentOptional = userRepo.findByEmail(user.getEmail());

		// check if email already exists

		if (studentOptional.isPresent()) {
			 throw new UserAlreadyExistsException("User is already registered");
		}
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepo.save(user);
		
		System.out.println(user.getPassword());
		
		
			
		return new ApiResponse(true, "User Registered Successfully");

	}
	
	public static int generateUserId() {
		int userId = (int)(Math.random() * 900000)+100000;
		
		return userId;
	}
	


}
