package com.school.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.Entity.User;
import com.school.repository.UserRepository;

@Service
public class ProfileImageService {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private UserRepository userRepo;
	
	public Boolean uploadProfileImage(MultipartFile file, int userId)
		
		throws IOException
	{
		
		
//		check is file selected
		if(file == null || file.isEmpty()) {
			return false;
		}
		
		Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		
//		check is path not exists
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		String originalFileName = file.getOriginalFilename();
		String extension = "";
		String fileName="";
		
		if(originalFileName != null && originalFileName.contains(".")) {
			extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			 fileName = userId + "_" + extension;
			
			Path targetLocation = uploadPath.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		}
		
		
		User user = userRepo.findById(userId);
		user.setProfileImageUrl(fileName);
		userRepo.save(user);
		System.out.println(uploadPath+ fileName);
		
		
		return true;
	}
}
