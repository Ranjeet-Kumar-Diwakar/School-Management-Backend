package com.school.service;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.school.Entity.User;
import com.school.dto.SignUpRequest;
import com.school.exception.UserAlreadyExistsException;
import com.school.payload.ApiResponse;
import com.school.repository.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {


	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	private static int userId;

	private static int rollNumber;
	
	
	public int generateUserId() {
		System.out.println("user repo" + userRepo);
		 Integer maxUserId = userRepo.findMaxId();
		 int newId;
		 
		 if (maxUserId == null) {
			 int year = LocalDateTime.now().getYear();
			 String regId = String.valueOf(year);
			 maxUserId = 01;
			 newId = Integer.parseInt(regId + String.valueOf(maxUserId));
		} else {
			newId = maxUserId+1;
		}
		 
		return newId;
	}
	
	public int generateStudentRollNo() {
		Integer maxRollNo = userRepo.findMaxRollNumber();
		int newRollNo;
		
		if(maxRollNo == null) {
			int year = LocalDateTime.now().getYear();
			maxRollNo = 001;
			newRollNo = Integer.parseInt(String.valueOf(year) + String.valueOf(maxRollNo));
		} else {
			newRollNo = maxRollNo+1;
		}
		
		return newRollNo;
	}
	
	
	@Override
	public ResponseEntity<ApiResponse> registerStudent(SignUpRequest signUpRequest) {
		
		User user = new User();
		
		System.out.println("---------------------------------");
		System.out.println(userRepo);

		Optional<User> studentOptional = userRepo.findByEmail(signUpRequest.getEmail());

		// check if email already exists

		if (studentOptional.isPresent()) {
			 throw new UserAlreadyExistsException("User is already registered");
		}
		
//		RegisterUserServiceImpl regService = new RegisterUserServiceImpl();
		
		int userId =	generateUserId();
		int	rollNo =	generateStudentRollNo();
		user.setId(userId);
		user.setRollNumber(rollNo);
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setRole(signUpRequest.getRole());
		user.setGender(signUpRequest.getGender());
		user.setPhone(signUpRequest.getPhone());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		
		userRepo.save(user);
		
		System.out.println("register user service implemantion called" + user);
		
		
			
		return ResponseEntity.ok()
							.body(new ApiResponse(true, "User Registered Successfully"));

	}
	
	


}
