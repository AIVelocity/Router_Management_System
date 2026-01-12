package com.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.AreaMaster;

public interface AreaRepository extends JpaRepository<AreaMaster, Long> {

	

	boolean save(String areaName);

	boolean existsByAreaName(String areaName);

}
