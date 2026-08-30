package com.school.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.payload.ApiResponse;
import com.school.service.CloudinaryService;

@RestController
@RequestMapping("/api/user")
public class CloudinaryController {
	
	private final CloudinaryService cloudinaryService;
	private Map result;

	public CloudinaryController(CloudinaryService cloudinaryService) {
		this.cloudinaryService = cloudinaryService;
	}
	
	@PostMapping("/upload-profile-image")
	public ResponseEntity<ApiResponse> uploadProfileImage(
				@RequestParam("file") MultipartFile file,
				Authentication authentication
				
			) {
		
		System.out.println("========== UPLOAD CONTROLLER HIT ==========");
		
		try {
			String email = authentication.getName();
			Map result = cloudinaryService.uploadImage(email, file);
			
			return ResponseEntity.ok(new ApiResponse(true, "upload success"));
			
		} catch (IOException ex) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponse(false, "upload failed"));
		}
	}
}
