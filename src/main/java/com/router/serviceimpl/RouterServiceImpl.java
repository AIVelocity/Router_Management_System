package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.dto.RequestAddRouter;
import com.router.entity.DepartmentMaster;
import com.router.entity.OrganizationMaster;
import com.router.entity.RouterMaster;
import com.router.entity.RouterOrganizationDepartment;
import com.router.repository.DepartmentRepository;
import com.router.repository.RouterMasterRepository;
import com.router.repository.RouterOrganizationDepartmentRepository;
import com.router.service.RouterService;

import jakarta.transaction.Transactional;

@Service
public class RouterServiceImpl implements RouterService{
	
	@Autowired
	private RouterMasterRepository routerMasterRepository;
	
	@Autowired
	private RouterOrganizationDepartmentRepository routerOrganizationDepartmentRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> createRouter(RequestAddRouter addRouterDetails) {
		
		Map<String, Object> response = new HashMap<>();
		try {
			
			System.out.println("Good yet");
		RouterMaster routerMaster=new RouterMaster();
		routerMaster.setRouterSerialNumber(addRouterDetails.getRouterSerialNumber());
		routerMaster.setRouterName(addRouterDetails.getRouterName());
		RouterMaster savedRouter = routerMasterRepository.save(routerMaster);
		System.out.println("Good yet1");

		 System.out.println("Good yet2");
		RouterOrganizationDepartment addRouter=new RouterOrganizationDepartment();
		OrganizationMaster organizationMaster=new OrganizationMaster();
		organizationMaster.setOrgId(addRouterDetails.getOrgId());
		addRouter.setOrganizationMaster(organizationMaster);
		if (addRouterDetails.getDeptId() != null) {
            DepartmentMaster department = departmentRepository
                    .findById(addRouterDetails.getDeptId())
                    .orElseThrow(() ->
                            new RuntimeException("Department not found with id: " + addRouterDetails.getDeptId())
                    );

            addRouter.setDepartmentMaster(department);
        } else {
            addRouter.setDepartmentMaster(null); 
        }
		System.out.println("Good yet3");
		addRouter.setRouterMaster(savedRouter);
		System.out.println("Good yet4");
		routerOrganizationDepartmentRepository.save(addRouter);
		
		response.put("status", true);
        response.put("message", "Router created successfully");
        response.put("data", savedRouter);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}catch (Exception e) {
	        response.put("status", false);
	        response.put("message", "Failed to create router");
	        response.put("error", e.getMessage());

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> getRouterList(RequestAddRouter addRouterDetails) {
		
		

		    Map<String, Object> response = new HashMap<>();

		    try {
		        // OrgId is mandatory
		        if (addRouterDetails.getOrgId() == null || addRouterDetails.getOrgId() == 0) {
		            response.put("status", false);
		            response.put("message", "Organization Id is required");
		            return ResponseEntity.badRequest().body(response);
		        }

		        List<RouterOrganizationDepartment> routerList;

		        // 1️⃣ If routerId is present → specific router
		        if (addRouterDetails.getRouterId() != null && addRouterDetails.getRouterId() != 0) {

		            routerList = routerOrganizationDepartmentRepository
		                    .findByOrganizationMasterOrgIdAndRouterMasterRouterId(addRouterDetails.getOrgId(), addRouterDetails.getRouterId());

		        }
		        // 2️⃣ If deptId is present → routers under dept
		        else if (addRouterDetails.getDeptId() != null && addRouterDetails.getDeptId() != 0) {

		            routerList = routerOrganizationDepartmentRepository
		                    .findByOrganizationMasterOrgIdAndDepartmentMasterDeptId(addRouterDetails.getOrgId(), addRouterDetails.getDeptId());

		        }
		        // 3️⃣ Only orgId → all routers under org
		        else {
		            routerList = routerOrganizationDepartmentRepository
		                    .findByOrganizationMasterOrgId(addRouterDetails.getOrgId());
		        }

		        response.put("status", true);
		        response.put("message", "Router list fetched successfully");
		        response.put("data", routerList);

		        return ResponseEntity.ok(response);

		    } catch (Exception e) {
		        response.put("status", false);
		        response.put("message", "Failed to fetch router list");
		        response.put("error", e.getMessage());
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		    }
	}


}
