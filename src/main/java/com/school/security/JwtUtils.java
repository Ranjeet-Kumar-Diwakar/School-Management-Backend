package com.school.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.school.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecretKey;

	@Value("${jwt.access.expiration:600000}")   // 10 min default
	private long accessTokenExpiration;

	@Value("${jwt.refresh.expiration:604800000}") // 7 days default
	private long refreshTokenExpiration;

	// secret key convert into object
	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(
				jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}

	// ---------- TOKEN GENERATION ----------

	public String generateAccessToken(User user) {
		return Jwts.builder()
				.subject(user.getEmail())
				.claim("id", user.getId().toString())
				.claim("role", user.getRole())  // adjust field name as per your User entity
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
				.signWith(getSecretKey())
				.compact();
	}

	public String generateRefreshToken(User user) {
		return Jwts.builder()
				.subject(user.getEmail())
				.claim("id", user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
				.signWith(getSecretKey())
				.compact();
	}

	// ---------- CLAIM EXTRACTION ----------

	public Claims getAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public String getIdFromToken(String token) {
		return getClaim(token, claims -> claims.get("id", String.class));
	}

	public String getRoleFromToken(String token) {
		return getClaim(token, claims -> claims.get("role", String.class));
	}

	public Date getExpirationFromToken(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public Date getIssuedAtFromToken(String token) {
		return getClaim(token, Claims::getIssuedAt);
	}

	// ---------- VALIDATION ----------

	public boolean isTokenExpired(String token) {
		try {
			return getExpirationFromToken(token).before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			// token expired
			return false;
		} catch (io.jsonwebtoken.security.SecurityException
				| io.jsonwebtoken.MalformedJwtException e) {
					System.out.println(e.getMessage());
			return false;
		} catch (io.jsonwebtoken.UnsupportedJwtException e) {
			return false;
		} catch (IllegalArgumentException e) {
			System.out.println("Null Claims");
			return false;
		}
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		try {
			String username = getUsernameFromToken(token);
			System.out.println("token wala user "+ username);
			System.out.println("logged in user "+ userDetails.getUsername());
			boolean result = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
			System.out.println("token validation result "+ result);
			return result;
		} catch (Exception e) {
			return false;
		}
	}

	// ---------- HEADER HELPER ----------

	public String extractTokenFromHeader(String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
}