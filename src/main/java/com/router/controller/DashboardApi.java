package com.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.service.DashboardService;

@RestController
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
@RequestMapping("/dashboard")
public class DashboardApi {

	@Autowired
	DashboardService dashboardService;
	
	@GetMapping("/org-summary")
	public ResponseEntity<String> getDashboardData() {
		
		return dashboardService.getDashboardData();
		
	}
	
	@GetMapping("/device-location")
	public ResponseEntity<String> getAllDeviceLocation() {
		
		return dashboardService.getAllDeviceLocation();
		
	}
	
	
}
