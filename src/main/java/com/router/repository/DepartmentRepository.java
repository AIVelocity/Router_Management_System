package com.router.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.DepartmentMaster;

public interface DepartmentRepository extends JpaRepository<DepartmentMaster, Long>{

	Optional<DepartmentMaster> findByDepartmentName(String departmentName);
	
}
