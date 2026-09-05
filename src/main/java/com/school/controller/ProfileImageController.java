package com.school.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.school.payload.ApiResponse;
import com.school.service.GetProfileService;
import com.school.service.ProfileImageService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/user")
public class ProfileImageController {
	
	@Autowired
	private ProfileImageService uploadImageService;
	
	@Autowired
	private GetProfileService getprofileService;
	
	@PostMapping("/upload-profile-image")
	public ResponseEntity<ApiResponse> uploadProfileImage(
			@RequestParam("userId") int userId,
			@RequestParam("file") MultipartFile file
			) {
			ApiResponse apiResponse = new ApiResponse();
		
		try {
			
			boolean response = uploadImageService.uploadProfileImage(file, userId);
			
			System.out.println("**********************************");
			System.out.println("response "+ response);
			System.out.println("profile image upload success");
			System.out.println(userId);
			System.out.println(file.getOriginalFilename());
			
			
			if(response) {
				apiResponse.setSuccess(true);
				apiResponse.setMessage("Profile Image Upload Successfully");
			} 
			else {
				apiResponse.setSuccess(false);
				apiResponse.setMessage("Please select a image");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(apiResponse);
	}
	
	
//	get profile image
	
	@GetMapping("/get-profile-image/{userId}")
	public ResponseEntity<Resource> getProfileImage(@PathVariable("userId") int userId) throws IOException {
		
//		GetProfileImageResponse profileImageResponse = new GetProfileImageResponse();
		try {
			Resource resource = getprofileService.getUserProfileImage(userId);
//			profileImageResponse.setProfileImage(profileImage);
//			profileImageResponse.setApiResponse(new ApiResponse(true, "profile image get successfully "));
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(resource);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
}
