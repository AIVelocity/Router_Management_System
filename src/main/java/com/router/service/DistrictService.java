package com.router.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.entity.DistrictMaster;

public interface DistrictService {

	//List<DistrictMaster> getDistrictList(Long stateId);
	ResponseEntity<Map<String, Object>> getDistrictList(Long stateId);

}
