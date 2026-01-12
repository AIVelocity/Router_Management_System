package com.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.CityMaster;

public interface CityRepository extends JpaRepository<CityMaster, Long>{
	

	boolean save(String cityName);

	boolean existsByCityNameAndDistrictMaster_DistrictId(String cityName, Long districtId);

}
