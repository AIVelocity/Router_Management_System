package com.router.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.service.DashboardService;
import com.router.util.DashboardUtil;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	DashboardUtil dashboardUtil;

	@Override
	public ResponseEntity<String> getDashboardData() {
		
		return dashboardUtil.getDashboardSummary();
	}

	@Override
	public ResponseEntity<String> getAllDeviceLocation() {
		// TODO Auto-generated method stub
		return dashboardUtil.getAllDeviceLocation();
	}

}
