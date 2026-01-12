package com.router.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.entity.CountryMaster;

public interface CountryService {

	//List<CountryMaster> getCountryList();
	ResponseEntity<Map<String, Object>> getCountryList();

}
