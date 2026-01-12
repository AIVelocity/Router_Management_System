package com.router.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.RbacMaster;

public interface RbacMasterRepository extends JpaRepository<RbacMaster, Long>{
	

	Optional<RbacMaster> findAllByRoleMasterRoleId(Long roleId);
	
	List<RbacMaster> findByRoleMasterRoleId(Long roleId);



}
