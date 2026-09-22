package com.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByEmail(String email);
	public List<User> findAll();
	public User findById(int userId);
	
	@Query("SELECT MAX(u.id) FROM User u")
	public Integer findMaxId();
	
	@Query("SELECT MAX(u.rollNumber) FROM User u")
	public Integer findMaxRollNumber();
}
