package com.router.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.DistrictMaster;

public interface DistrictRepository extends JpaRepository<DistrictMaster, Long> {

	List<DistrictMaster> findByStateMaster_StateId(Long stateId);
	
	

}
