package com.school.security;

import java.util.Set;

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
import com.school.dto.UserResponse;
import com.school.enums.Permission;
import com.school.enums.Role;
import com.school.payload.ApiResponse;
import com.school.service.RolePermissionService;

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
		
			String token = jwtUtils.generateAccessToken(user);
			
			System.out.println("login " +token);
				
			System.out.println(user.getRole());
			Set<Permission> permission = RolePermissionService.ROLE_PERMISSION.get(Role.valueOf(user.getRole()));
			System.out.println(RolePermissionService.ROLE_PERMISSION);
			
			UserResponse userResponse = new UserResponse();
			
			userResponse.setId(user.getId());
			userResponse.setName(user.getName());
			userResponse.setEmail(user.getEmail());
			userResponse.setPhone(user.getPhone());
			userResponse.setRole(user.getRole());
			userResponse.setGender(user.getGender());
			
		
		return new LoginResponse(
				true, 
				"login Success", 
				token, 
				userResponse, 
				permission
			);
	}

	
}
