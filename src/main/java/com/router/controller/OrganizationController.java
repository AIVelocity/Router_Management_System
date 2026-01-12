package com.router.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.router.dto.RequestAddOrganization;
import com.router.entity.CountryMaster;
import com.router.entity.DistrictMaster;
import com.router.entity.OrganizationMaster;
import com.router.entity.StateMaster;
import com.router.service.CountryService;
import com.router.service.DistrictService;
import com.router.service.OrganizationService;
import com.router.service.StateService;

@RestController
@RequestMapping("/org")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@PostMapping("/add-organization")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> createOrganization(
	        @RequestBody RequestAddOrganization orgDetails) {

		 return organizationService.createOrganization(orgDetails);

//	    try {
//	        boolean isCreated = organizationService.createOrganization(orgDetails);
//
//	        if (isCreated) {
//	            response.put("success", true);
//	            response.put("message", "Organization created successfully");
//
//	            return ResponseEntity
//	                    .status(HttpStatus.CREATED)
//	                    .body(response);
//	        } else {
//	            response.put("success", false);
//	            response.put("message", "Organization creation failed");
//
//	            return ResponseEntity
//	                    .status(HttpStatus.BAD_REQUEST)
//	                    .body(response);
//	        }
//
//	    } catch (Exception ex) {
//	        response.put("success", false);
//	        response.put("message", "Internal server error");
//	        ex.printStackTrace();
//
//	        return ResponseEntity
//	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body(response);
//	    }
	    
	   
	}
	
	@PutMapping("/add-hierarchy/{organizationId}")
	// @PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> addOrganizationHierarchyId(
	        @PathVariable Long organizationId,
	        @RequestBody RequestAddOrganization orgDetails) {

	  return organizationService.addOrganizationHierarchyId(organizationId, orgDetails);
//	    organizationService.addOrganizationHierarchyId(organizationId, orgDetails);
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("success", true);
//	    response.put("message", "Organization hierarchy updated successfully");
//
//	    return ResponseEntity
//	            .status(HttpStatus.OK)
//	            .body(response);
	}

	
	@GetMapping("/org-details")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getOrganizationDetails() {
		return organizationService.getOrganizationDetails();
	    //List<OrganizationMaster> allOrganizationDetails = organizationService.getOrganizationDetails();
	   // return ResponseEntity.ok(allOrganizationDetails);
	}
	
	@GetMapping("/org-details/{organizationId}")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getOrganizationById(
	        @PathVariable("organizationId") Long organizationId) {
	 return organizationService.getIdBasedOrganizationDetails(organizationId);
	   // OrganizationMaster organizationDetailsById = organizationService.getIdBasedOrganizationDetails(organizationId);
	   // return ResponseEntity.ok(organizationDetailsById);
	}

	
	@GetMapping("/country")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getCountryList() {
		return countryService.getCountryList();
//	    List<CountryMaster> countries = countryService.getCountryList();
//	    return ResponseEntity.ok(countries);
	}
	
	@GetMapping("/state")
	//@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getStateList(@RequestParam Long countryId) {
		return stateService.getStateList(countryId);
	   // List<StateMaster> states = stateService.getStateList(countryId);
	   // return ResponseEntity.ok(states);
	}
	
	@GetMapping("/district")
//	@PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getDistrictList(@RequestParam Long stateId) {
		
		return districtService.getDistrictList(stateId);
	   // List<DistrictMaster> districts = districtService.getDistrictList(stateId);
	   // return ResponseEntity.ok(districts);
	}
}

