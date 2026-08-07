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
		
			Role.ROLE_ADMIN,  //key
			EnumSet.allOf(Permission.class), //value
			
			Role.ROLE_TEACHER,
			EnumSet.of(
						Permission.ATTENDENCE_VIEW,
						Permission.SUBJECT_VIEW,
						Permission.EXAMINATION_VIEW,
						Permission.NOTICE_BOARD_VIEW,
						Permission.CLASS_VIEW,
						Permission.EVENTS_VIEW
			),
			
			Role.ROLE_STUDENT,
			EnumSet.of(
						Permission.ATTENDENCE_VIEW,
						Permission.EXAMINATION_VIEW,
						Permission.NOTICE_BOARD_VIEW,
						Permission.FEES_VIEW,
						Permission.EVENTS_VIEW
						),
			
			Role.ROLE_PRINCIPAL,
			EnumSet.of(
						Permission.STUDENT_VIEW,
						Permission.TEACHER_VIEW,
						Permission.STAFF_VIEW,
						Permission.CLASS_VIEW,
						Permission.SUBJECT_VIEW,
						Permission.ATTENDENCE_VIEW,
						Permission.EXAMINATION_VIEW
						)
			
			
			
	);
}
