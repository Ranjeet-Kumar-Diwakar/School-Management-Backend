package com.school.service;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.school.enums.Permission;
import com.school.enums.Role;

@Service
public class RolePermissionService {
	
	public static final Map<Role, Set<Permission>> ROLE_PERMISSION = Map.of(
		
			Role.ADMIN,  //key
			EnumSet.allOf(Permission.class), //value
			
			Role.TEACHER,
			EnumSet.of(
						Permission.ATTENDENCE,
						Permission.SUBJECT,
						Permission.EXAMINATION,
						Permission.NOTICEBOARD,
						Permission.CLASS,
						Permission.EVENTS
			),
			
			Role.STUDENT,
			EnumSet.of(
						Permission.ATTENDENCE,
						Permission.EXAMINATION,
						Permission.NOTICEBOARD,
						Permission.FEES,
						Permission.EVENTS
						),
			
			Role.PRINCIPAL,
			EnumSet.of(
						Permission.STUDENT,
						Permission.TEACHER,
						Permission.STAFF,
						Permission.CLASS,
						Permission.SUBJECT,
						Permission.ATTENDENCE,
						Permission.EXAMINATION
						)
			
			
			
	);
}
