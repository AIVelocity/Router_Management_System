package com.router.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.router.dto.RequestAddDepartment;
import com.router.entity.DepartmentMaster;
import com.router.entity.OrganizationDept;
import com.router.entity.OrganizationMaster;
import com.router.repository.DepartmentRepository;
import com.router.repository.OrganizationDeptRepository;
import com.router.repository.OrganizationRepository;
import com.router.service.DepartmentService;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private OrganizationDeptRepository organizationDeptRepository;
	
	
	

	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> addDepartmentToOrganization(RequestAddDepartment request) {

	    Map<String, Object> response = new HashMap<>();

	    // 1️⃣ Fetch Organization
	    OrganizationMaster organization = organizationRepository
	            .findById(request.getOrgId())
	            .orElseThrow(() -> new RuntimeException("Organization not found"));

	    // 2️⃣ Check Department exists → else create and return it
	    DepartmentMaster department = departmentRepository
	            .findByDepartmentName(request.getDepartmentName())
	            .orElseGet(() -> {
	                DepartmentMaster newDept = new DepartmentMaster();
	                newDept.setDepartmentName(request.getDepartmentName());
	                return departmentRepository.save(newDept); // ✅ return saved entity
	            });

	    // 3️⃣ Prevent duplicate mapping
	    boolean exists = organizationDeptRepository
	            .existsByOrganizationMasterAndDepartmentMaster(organization, department);

	    if (exists) {
	        response.put("status", false);
	        response.put("message", "Department already mapped to organization");
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(response);
	    }

	    // 4️⃣ Map department to organization
	    OrganizationDept orgDept = new OrganizationDept();
	    orgDept.setOrganizationMaster(organization);
	    orgDept.setDepartmentMaster(department);

	    OrganizationDept save = organizationDeptRepository.save(orgDept);

	    // 5️⃣ Build success response
	    response.put("status", true);
	    response.put("message", "Department successfully added to organization");
	    response.put("organizationId", organization.getOrgId());
	    response.put("departmentId", department.getDeptId());
	    response.put("departmentName", department.getDepartmentName());

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(response);
	}



	@Override
	public ResponseEntity<Map<String, Object>> getDepartmentList() {

	    List<DepartmentMaster> deptList = departmentRepository.findAll();

	    if (deptList.isEmpty()) {
	        throw new RuntimeException("No departments found");
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    response.put("message", "Department list fetched successfully");
	    response.put("data", deptList);

	    return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(response);
	}



}
