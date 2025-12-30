package com.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashboardApi {
	
	@Autowired
	DashboardService dashboardService;
	
	@GetMapping("/testApp")
	public ResponseEntity<String> testAppRunning() {
		log.info("testApp initialized");
		String response = "Dashboard API is running successfully.";
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/org-summary")
	public ResponseEntity<String> getDashboardData() {
		log.info("getDashboardData initialized");
		return dashboardService.getDashboardData();
		
	}
	
	@GetMapping("/device-location")
	public ResponseEntity<String> getAllDeviceLocation() {
		log.info("getAllDeviceLocation initialized");
		return dashboardService.getAllDeviceLocation();
		
	}
	
	
}
