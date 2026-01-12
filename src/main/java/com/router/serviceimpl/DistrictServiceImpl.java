package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.entity.DistrictMaster;
import com.router.repository.DistrictRepository;
import com.router.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService{
	@Autowired
	private DistrictRepository districtRepository;

//	@Override
//	public List<DistrictMaster> getDistrictList(Long stateId) {
//		
//		return districtRepository.findByStateMaster_StateId(stateId);
//	}
	@Override
	public ResponseEntity<Map<String, Object>> getDistrictList(Long stateId) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ------------------- Null Check -------------------
	        if (stateId == null) {
	            response.put("status", false);
	            response.put("message", "stateId cannot be null");
	            return ResponseEntity.badRequest().body(response);
	        }

	        // ------------------- Fetch Districts -------------------
	        List<DistrictMaster> districts =
	                districtRepository.findByStateMaster_StateId(stateId);

	        if (districts == null || districts.isEmpty()) {
	            response.put("status", false);
	            response.put("message", "No districts found for given state");
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	        }

	        // ------------------- Success Response -------------------
	        response.put("status", true);
	        response.put("data", districts);
	        response.put("count", districts.size());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to fetch district list");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


}
