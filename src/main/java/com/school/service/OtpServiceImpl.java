package com.school.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.Entity.EmailOtp;
import com.school.repository.OtpRepository;

@Service
public class OtpServiceImpl implements OtpService {
	
	private final SecureRandom secureRandom = new SecureRandom();
	
	@Autowired
	private OtpRepository otpRepo;
	

	@Override
	public int generateOtp(String email) {
		
		int otp = (int)(Math.random() * 900000)+100000;
//		int otp = 100000 + secureRandom.nextInt(900000);
		
		Optional<EmailOtp> userByEmail = otpRepo.findByEmail(email);
		
		if(userByEmail.isPresent()) {
			EmailOtp existingUser = userByEmail.get();
			existingUser.setOtp(otp);
			existingUser.setEmail(email);
			existingUser.setGenerateAt(LocalDateTime.now());
			existingUser.setExpiryAt(existingUser.getGenerateAt().plusMinutes(5));
			existingUser.setVerified(false);
			
			otpRepo.save(existingUser);
			
		} 
		
		if(userByEmail.isEmpty()) {
			
			EmailOtp newUser = new EmailOtp();
			newUser.setOtp(otp);
			newUser.setEmail(email);
			newUser.setGenerateAt(LocalDateTime.now());
			newUser.setExpiryAt(newUser.getGenerateAt().plusMinutes(5));
			newUser.setVerified(false);
			
			otpRepo.save(newUser);
		}
		
		
		
		
		
		return otp;
	}

	@Override
	public boolean validateOtp(String email, int otp) {
		
		Optional<EmailOtp> userOptional = otpRepo.findByEmail(email);
		
		if (userOptional.isEmpty()) {
			System.out.println("Record not found for id " + email);
			System.out.println("optional " + userOptional.get());
			return false;
		}
		
		EmailOtp emailOtp = userOptional.get();
		
		
		boolean exprired = LocalDateTime.now().isBefore(emailOtp.getExpiryAt());
		
		if (exprired && emailOtp.getOtp() == otp) {
			System.out.println("otp verification success");
			emailOtp.setVerified(true);
			otpRepo.save(emailOtp);
			
		} else {
			System.out.println("Invalid Otp");
			return false;
		}
		
		return true;
	}

	
}
