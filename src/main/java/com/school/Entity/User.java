package com.school.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {

	@Id
	private Integer id;
	private int rollNumber;
	private String name;
	private String email;
	private String phone;
	private String role;
	private String gender;
	private String password;
	private String profileImageUrl;
	private String profileImagePublicId;
	
	
	

	public User(Integer id, int rollNumber, String name, String email, String phone, String role, String gender,
			String password, String profileImageUrl, String profileImagePublicId) {
		this.id = id;
		this.rollNumber = rollNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.gender = gender;
		this.password = password;
		this.profileImageUrl = profileImageUrl;
		this.profileImagePublicId = profileImagePublicId;
	}
	
	


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Integer getId() {
		return id;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getRole() {
		return role;
	}

	public String getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getProfileImagePublicId() {
		return profileImagePublicId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public void setName(String name) {
		this.name = name;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setProfileImagePublicId(String profileImagePublicId) {
		this.profileImagePublicId = profileImagePublicId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		return "RegisterUser [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", role=" + role
				+ ", gender=" + gender + ", password=" + password + "]";
	}


	
}
