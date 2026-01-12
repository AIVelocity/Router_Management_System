package com.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.DepartmentMaster;
import com.router.entity.OrganizationDept;
import com.router.entity.OrganizationMaster;

public interface OrganizationDeptRepository  extends JpaRepository<OrganizationDept, Long>{

	

	boolean existsByOrganizationMasterAndDepartmentMaster(OrganizationMaster organization, DepartmentMaster department);

}
