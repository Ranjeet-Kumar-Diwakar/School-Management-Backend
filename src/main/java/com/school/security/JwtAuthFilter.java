package com.school.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.Entity.User;
import com.school.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String requestTokenHeader = request.getHeader("Authorization");
		
		if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = requestTokenHeader.split("Bearer")[1].trim();
		String username = jwtUtils.getUsernameFromToken(token);
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
			
			UsernamePasswordAuthenticationToken AuthToken = 
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(AuthToken);
		}
		filterChain.doFilter(request, response);
	}
	
	
}
