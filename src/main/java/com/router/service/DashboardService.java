package com.router.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

	public ResponseEntity<String> getDashboardData();

	public ResponseEntity<String> getAllDeviceLocation();
}
