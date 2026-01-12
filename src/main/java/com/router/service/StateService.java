package com.router.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.entity.StateMaster;

public interface StateService {

//	List<StateMaster> getStateList(Long countryId);
	ResponseEntity<Map<String, Object>> getStateList(Long countryId);

}
