package com.school.security;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.school.Entity.User;
import com.school.dto.LoginRequest;
import com.school.dto.LoginResponse;
import com.school.payload.ApiResponse;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	public LoginResponse login(LoginRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(
			
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword())
			);
		
		System.out.println("auth service run");
		
		CustomUserDetails userDetails  = (CustomUserDetails ) authentication.getPrincipal();
			User user = userDetails.getUser();
		
			String token = jwtUtils.generateAceessToken(user);
			
		
		return new LoginResponse(true, "login Success", token);
	}

	
}
