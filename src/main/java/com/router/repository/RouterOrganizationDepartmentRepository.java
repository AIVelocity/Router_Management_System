package com.router.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.RouterOrganizationDepartment;

public interface RouterOrganizationDepartmentRepository extends JpaRepository<RouterOrganizationDepartment, Long> {
	
	 List<RouterOrganizationDepartment> findByOrganizationMasterOrgIdAndRouterMasterRouterId(Long orgId, Long routerId);

	   
	    List<RouterOrganizationDepartment> findByOrganizationMasterOrgIdAndDepartmentMasterDeptId(Long orgId, Long deptId);

	   
	    List<RouterOrganizationDepartment> findByOrganizationMasterOrgId(Long orgId);
}
