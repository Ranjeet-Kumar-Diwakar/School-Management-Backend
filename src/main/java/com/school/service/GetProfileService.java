package com.school.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.school.Entity.User;
import com.school.repository.UserRepository;

@Service
public class GetProfileService {

	@Autowired
	private UserRepository userRepo;
	
	
	public Resource getUserProfileImage(int userId) throws IOException{
		
		User user = userRepo.findById(userId);
		
		Path path = Paths.get("uploads/images")
				.resolve(user.getProfileImageUrl())
				.normalize()
				.toAbsolutePath();
		
		 	System.out.println("FILE PATH = " + path);
		    System.out.println("EXISTS = " + Files.exists(path));
		    System.out.println("READABLE = " + Files.isReadable(path));
		
		Resource resource = new UrlResource(path.toUri());
		
		System.out.println("..............................................");
		System.out.println("getting file ..." + resource.getFilename());
		
		return resource;
	}
}
