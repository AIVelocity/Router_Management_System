package com.router.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.dto.RequestAddDepartment;
import com.router.dto.RequestAddOrganization;
import com.router.entity.DepartmentMaster;
import com.router.service.DepartmentService;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/department-list")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getDepartmentList() {
		return departmentService.getDepartmentList();

	   
	}
	
	@PostMapping("/add-department")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> addDepartment(
	        @RequestBody RequestAddDepartment deptDetails) {
		return departmentService.addDepartmentToOrganization(deptDetails);

	   
	}
	
	
}
