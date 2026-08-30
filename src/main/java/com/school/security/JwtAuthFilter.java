package com.school.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.Entity.User;
import com.school.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		// No token, or malformed header -> just continue unauthenticated
		if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = requestTokenHeader.substring(7).trim();

		try {
			// Only proceed if there's no existing authentication in context
			if (SecurityContextHolder.getContext().getAuthentication() == null) {

				String username = jwtUtils.getUsernameFromToken(token);

				if (username != null) {
//					User user = userRepo.findByEmail(username)
					UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

					// Validate token signature, expiry, and subject match BEFORE trusting it
					if (jwtUtils.validateToken(token, userDetails)) {

						UsernamePasswordAuthenticationToken authToken =
								new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

						SecurityContextHolder.getContext().setAuthentication(authToken);

						System.out.println("Authenticated user '{}' for request {}"+ username+ request.getRequestURI());
					} else {
						System.out.println("Token validation failed for user '{}'"+ username);
					}
				}
			}
		}
		catch (Exception ex) {
			System.out.println(" security context not set" + ex.getMessage());
		}
		filterChain.doFilter(request, response);
	}
//			SecurityContextHolder.clearContext();

}