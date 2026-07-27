package com.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.Entity.EmailOtp;

public interface OtpRepository extends JpaRepository<EmailOtp, Long>{
	
	Optional<EmailOtp> findByEmail(String Email);
}
