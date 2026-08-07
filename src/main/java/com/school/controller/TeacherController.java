package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.Entity.User;
import com.school.dto.LoginResponse;
import com.school.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class TeacherController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/all-users")
	public List<User> getAllUsers(){
		System.out.println("get all user running..");
		List<User> allUsers = userRepo.findAll();
		
		return allUsers;
	}
	
	
}
