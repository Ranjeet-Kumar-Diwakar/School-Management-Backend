package com.school.dto;

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

public class UserResponse {

		
		private Integer id;
		private String name;
		private String email;
		private String phone;
		private String role;
		private String gender;
		
		
		
		public UserResponse() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserResponse(Integer id, String name, String email, String phone, String role, String gender) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.role = role;
			this.gender = gender;
		}
		
		public Integer getId() {
			return id;
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
		public void setId(Integer id) {
			this.id = id;
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

		@Override
		public String toString() {
			return "UserResponse [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", role="
					+ role + ", gender=" + gender + "]";
		}
		
	
}
