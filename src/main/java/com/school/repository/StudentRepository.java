package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public List<Student> findAll();
}
