package com.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.OrganizationMaster;

public interface OrganizationRepository extends JpaRepository<OrganizationMaster, Long>{

	boolean existsByOrganizationName(String organizationName);

	boolean save(String organizationName);

}
