package com.school.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
	@Value("${cloudinary.cloud-name}")
	private String cloudName;
	
	@Value("${cloudinary.api-key}")
	private String apiKey;
	
	@Value("${cloudinary.api-secret}")
	private String apiSecretKey;
	
	
	
	@Bean
	public Cloudinary cloudinary() {
		
		System.out.println(cloudName);
		System.out.println(apiKey);
		System.out.println(apiSecretKey);
		
		return new Cloudinary(
				ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecretKey
			)
		);
	}
	
}
