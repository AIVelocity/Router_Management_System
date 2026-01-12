package com.router.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.dto.RequestAddOrganization;
import com.router.entity.OrganizationMaster;

public interface OrganizationService {

	//boolean createOrganization(RequestAddOrganization orgDetails);
	
	ResponseEntity<Map<String, Object>> createOrganization(RequestAddOrganization orgDetails);

	//List<OrganizationMaster> getOrganizationDetails();
	ResponseEntity<Map<String, Object>> getOrganizationDetails();

	//OrganizationMaster getIdBasedOrganizationDetails(Long organizationId);
	ResponseEntity<Map<String, Object>> getIdBasedOrganizationDetails(Long organizationId);

	//boolean addOrganizationHierarchyId(Long organizationId,RequestAddOrganization orgDetails);
	 ResponseEntity<Map<String, Object>> addOrganizationHierarchyId(Long organizationId,RequestAddOrganization orgDetails);

	


}
