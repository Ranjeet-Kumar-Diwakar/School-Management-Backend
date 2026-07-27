package com.school.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.Entity.User;
import com.school.exception.UserAlreadyExistsException;
import com.school.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		User user =	userRepo.findByEmail(username).orElseThrow(() -> 
		new UsernameNotFoundException("user not found")
				);
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		return new CustomUserDetails(user);
	}
	
	
}
