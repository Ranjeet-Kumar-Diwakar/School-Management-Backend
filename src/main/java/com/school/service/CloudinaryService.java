package com.school.service;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.school.Entity.User;
import com.school.repository.UserRepository;

@Service
public class CloudinaryService {
	
	
	private Cloudinary cloudinary;
	
	private UserRepository userRepo;

	public CloudinaryService(Cloudinary cloudinary) {
		super();
		this.cloudinary = cloudinary;
	}
	
//	upload method
	public Map uploadImage(String email, MultipartFile file) throws IOException {
		System.out.println("========== UPLOAD service HIT ==========");
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException());
		byte[] imageBytes = file.getBytes();
		Map imagePath = ObjectUtils.asMap("folder", "school-management/profile-images");
		
		System.out.println("UPLOAD success");
		
		Map result = cloudinary.uploader().upload(imageBytes, imagePath);
		
		String secure_url = (String) result.get("secure_url");
		String public_id = (String) result.get("public_id");
		
		user.setProfileImageUrl(secure_url);
		user.setProfileImagePublicId(public_id);
		
		userRepo.save(user);
		
		return result;
 	}
}
