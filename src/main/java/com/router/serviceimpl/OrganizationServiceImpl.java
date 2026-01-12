package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.dto.RequestAddOrganization;
import com.router.entity.AreaMaster;
import com.router.entity.CityMaster;
import com.router.entity.DistrictMaster;
import com.router.entity.OrganizationMaster;
import com.router.repository.AreaRepository;
import com.router.repository.CityRepository;
import com.router.repository.DistrictRepository;
import com.router.repository.OrganizationRepository;
import com.router.service.OrganizationService;

import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

//	@Override
//	public boolean createOrganization(RequestAddOrganization orgDetails) {
//
//		DistrictMaster district = districtRepository.findById(orgDetails.getDistrictId())
//				.orElseThrow(() -> new RuntimeException("District not found"));
//
//		
//		//Add city----------------------------------------------------------------------------
//		CityMaster city = new CityMaster();
//		city.setCityName(orgDetails.getCityName());
//		city.setDistrictMaster(district);
//
//		CityMaster isSaveCity = cityRepository.save(city); // ✅ ENTITY
//
//		
//		//Add Area----------------------------------------------------------------------------
//		AreaMaster area = new AreaMaster();
//		area.setAreaName(orgDetails.getAreaName());
//		area.setCityMaster(city);
//
//		AreaMaster isSaveArea = areaRepository.save(area); // ✅ ENTITY
//
//		 
//		//Add organization----------------------------------------------------------------------------
//		boolean isOrganizationExists = organizationRepository
//				.existsByOrganizationName(orgDetails.getOrganizationName());
//
//		if (isOrganizationExists) {
//			throw new RuntimeException("Organization already exists");
//		}
//		OrganizationMaster organization = new OrganizationMaster();
//		organization.setOrganizationName(orgDetails.getOrganizationName());
//		organization.setAreaMaster(area);
//
//		OrganizationMaster isSaveOrganization = organizationRepository.save(organization); // ✅ ENTITY
//
//		//boolean isSaveOrganization =organizationRepository.save(orgDetails.getOrganizationName());
//
//		if (isSaveCity != null && isSaveOrganization != null && isSaveArea != null) {
//			return true;
//		}
//
//		return false;
//	}
	
//	@Override
//	public boolean createOrganization(RequestAddOrganization orgDetails) {
//
//		 Map<String, Object> response = new HashMap<>();
//	    // ------------------- District -------------------
//		DistrictMaster district = districtRepository.findById(orgDetails.getDistrictId())
//			.orElseThrow(() -> new RuntimeException("District not found"));
//
//	    // ------------------- City -------------------
//	    CityMaster city = new CityMaster();
//	    city.setCityName(orgDetails.getCityName());
//	    city.setDistrictMaster(district);
//	    CityMaster savedCity = cityRepository.save(city);
//
//	    // ------------------- Area -------------------
//	    AreaMaster area = new AreaMaster();
//	    area.setAreaName(orgDetails.getAreaName());
//	    area.setCityMaster(savedCity);
//	    AreaMaster savedArea = areaRepository.save(area);
//
//	    // ------------------- Organization Exists Check -------------------
//	    if (organizationRepository.existsByOrganizationName(orgDetails.getOrganizationName())) {
//	        throw new RuntimeException("Organization already exists");
//	    }
//
//	   
//	    // ------------------- Organization -------------------
//	 //   ObjectMapper mapper = new ObjectMapper();
//	    OrganizationMaster organization = new OrganizationMaster();
//	    organization.setOrganizationName(orgDetails.getOrganizationName());
//	    organization.setAreaMaster(savedArea);
////	    organization.setHierarchyJson(
////	            mapper.writeValueAsString(orgDetails.getHierarchyJson())
////	    );   
//
//	    OrganizationMaster savedOrganization = organizationRepository.save(organization);
//
//	    return savedCity != null && savedArea != null && savedOrganization != null;
//	}

	@Override
	public ResponseEntity<Map<String, Object>> createOrganization(RequestAddOrganization orgDetails) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ------------------- Null Check -------------------
	        if (orgDetails == null) {
	            response.put("status", false);
	            response.put("message", "Request body cannot be null");
	            return ResponseEntity.badRequest().body(response);
	        }

	        // ------------------- District -------------------
	        DistrictMaster district = districtRepository.findById(orgDetails.getDistrictId())
	                .orElseThrow(() -> new RuntimeException("District not found"));

	        // ------------------- City -------------------
	        CityMaster city = new CityMaster();
	        city.setCityName(orgDetails.getCityName());
	        city.setDistrictMaster(district);
	        CityMaster savedCity = cityRepository.save(city);

	        // ------------------- Area -------------------
	        AreaMaster area = new AreaMaster();
	        area.setAreaName(orgDetails.getAreaName());
	        area.setCityMaster(savedCity);
	        AreaMaster savedArea = areaRepository.save(area);

	        // ------------------- Organization Exists Check -------------------
	        if (organizationRepository.existsByOrganizationName(orgDetails.getOrganizationName())) {
	            response.put("status", false);
	            response.put("message", "Organization already exists");
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	        }

	        // ------------------- Organization -------------------
	        OrganizationMaster organization = new OrganizationMaster();
	        organization.setOrganizationName(orgDetails.getOrganizationName());
	        organization.setAreaMaster(savedArea);

	        OrganizationMaster savedOrganization = organizationRepository.save(organization);

	        // ------------------- Success Response -------------------
	        response.put("status", true);
	        response.put("message", "Organization created successfully");
	        response.put("organizationId", savedOrganization.getOrgId());

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);

	    } catch (RuntimeException e) {
	        response.put("status", false);
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Internal server error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

//	@Override
//	public List<OrganizationMaster> getOrganizationDetails() {
//		
//		return organizationRepository.findAll();
//	}
	
	@Override
	public ResponseEntity<Map<String, Object>> getOrganizationDetails() {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        List<OrganizationMaster> organizations = organizationRepository.findAll();

	        if (organizations == null || organizations.isEmpty()) {
	            response.put("status", false);
	            response.put("message", "No organization records found");
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	        }

	        response.put("status", true);
	        response.put("data", organizations);
	        response.put("count", organizations.size());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Error while fetching organization details");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}



//	@Override
//	public OrganizationMaster getIdBasedOrganizationDetails(Long organizationId) {
//	
//		return organizationRepository.findById(organizationId)
//	            .orElseThrow(() ->
//	                    new RuntimeException("Organization not found with id: " + organizationId)
//	            );
//	}

	
	@Override
	public ResponseEntity<Map<String, Object>> getIdBasedOrganizationDetails(Long organizationId) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ------------------- Null Check -------------------
	        if (organizationId == null) {
	            response.put("status", false);
	            response.put("message", "Organization ID cannot be null");
	            return ResponseEntity.badRequest().body(response);
	        }

	        // ------------------- Fetch Organization -------------------
	        OrganizationMaster organization = organizationRepository.findById(organizationId)
	                .orElseThrow(() ->
	                        new RuntimeException("Organization not found with id: " + organizationId)
	                );

	        // ------------------- Success Response -------------------
	        response.put("status", true);
	        response.put("data", organization);

	        return ResponseEntity.ok(response);

	    } catch (RuntimeException e) {
	        response.put("status", false);
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to fetch organization details");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	

//	@Override
//	@Transactional
//	public boolean addOrganizationHierarchyId(
//	        Long organizationId,
//	        RequestAddOrganization orgDetails) {
//
//	    OrganizationMaster organization = organizationRepository
//	            .findById(organizationId)
//	            .orElseThrow(() -> new RuntimeException("Organization not found"));
//
//	    try {
//	        String hierarchyJson = objectMapper
//	                .writeValueAsString(orgDetails.getHierarchyJson());
//
//	        organization.setHierarchyJson(hierarchyJson);
//
//	        organizationRepository.save(organization);
//
//	        return true;
//
//	    } catch (Exception e) {
//	        throw new RuntimeException("Failed to save hierarchy JSON", e);
//	    }
//	}
	
	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> addOrganizationHierarchyId(
	        Long organizationId,
	        RequestAddOrganization orgDetails) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ------------------- Null Checks -------------------
	        if (organizationId == null) {
	            response.put("status", false);
	            response.put("message", "Organization ID cannot be null");
	            return ResponseEntity.badRequest().body(response);
	        }

	        if (orgDetails == null || orgDetails.getHierarchyJson() == null) {
	            response.put("status", false);
	            response.put("message", "Hierarchy data is required");
	            return ResponseEntity.badRequest().body(response);
	        }

	        // ------------------- Organization Fetch -------------------
	        OrganizationMaster organization = organizationRepository
	                .findById(organizationId)
	                .orElseThrow(() -> new RuntimeException("Organization not found"));

	        // ------------------- Convert JSON -------------------
	        String hierarchyJson = objectMapper
	                .writeValueAsString(orgDetails.getHierarchyJson());

	        organization.setHierarchyJson(hierarchyJson);

	        organizationRepository.save(organization);

	        // ------------------- Success Response -------------------
	        response.put("status", true);
	        response.put("message", "Organization hierarchy saved successfully");
	        response.put("organizationId", organization.getOrgId());

	        return ResponseEntity.ok(response);

	    } catch (RuntimeException e) {
	        response.put("status", false);
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	    } catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to save hierarchy JSON");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}




	


}
