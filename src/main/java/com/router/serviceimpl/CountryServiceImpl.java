package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.entity.CountryMaster;
import com.router.repository.CountryRepository;
import com.router.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;

//	@Override
//	public List<CountryMaster> getCountryList() {
//		
//		return countryRepository.findAll();
//	}
	
	@Override
	public ResponseEntity<Map<String, Object>> getCountryList() {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        List<CountryMaster> countries = countryRepository.findAll();

	        if (countries == null || countries.isEmpty()) {
	            response.put("status", false);
	            response.put("message", "No countries found");
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	        }

	        response.put("status", true);
	        response.put("data", countries);
	        response.put("count", countries.size());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to fetch country list");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


}
