package com.school.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.school.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecretKey;
	
	//secret key convert into object
	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(
				jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
//	generate jwt token
	
	public String generateAceessToken(User user) {
		
		String token = Jwts.builder()
						.subject(user.getEmail())
						.claim("id", user.getId().toString())
						.issuedAt(new Date())
						.expiration(new Date(System.currentTimeMillis() + 1000*60*10))
						.signWith(getSecretKey())
						.compact();
		
		
		return token;
	}
	
	// get username from token
	
	public String getUsernameFromToken(String token) {
		
		Claims claims = Jwts.parser()
		.verifyWith(getSecretKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
		
		return claims.getSubject();
	}
	
	
	
	
	
	
	
	
	
}
