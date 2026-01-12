package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.entity.StateMaster;
import com.router.repository.StateRepository;
import com.router.service.StateService;

@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateRepository stateRepository;

//	@Override
//	public List<StateMaster> getStateList(Long countryId) {
//		
//		if (countryId == null) {
//	        throw new IllegalArgumentException("countryId cannot be null");
//	    }
//
//	    return stateRepository.findByCountryMaster_CountryId(countryId);
//		  }
	
	@Override
	public ResponseEntity<Map<String, Object>> getStateList(Long countryId) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ------------------- Null Check -------------------
	        if (countryId == null) {
	            response.put("status", false);
	            response.put("message", "countryId cannot be null");
	            return ResponseEntity.badRequest().body(response);
	        }

	        // ------------------- Fetch States -------------------
	        List<StateMaster> states =
	                stateRepository.findByCountryMaster_CountryId(countryId);

	        if (states == null || states.isEmpty()) {
	            response.put("status", false);
	            response.put("message", "No states found for given country");
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	        }

	        // ------------------- Success Response -------------------
	        response.put("status", true);
	        response.put("data", states);
	        response.put("count", states.size());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to fetch state list");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


}
