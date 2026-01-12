package com.router.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.dto.RequestAddDepartment;

public interface DepartmentService {

	ResponseEntity<Map<String, Object>> addDepartmentToOrganization(RequestAddDepartment deptDetails);

	ResponseEntity<Map<String, Object>> getDepartmentList();

}
