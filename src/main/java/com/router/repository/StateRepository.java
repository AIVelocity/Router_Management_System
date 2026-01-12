package com.router.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.StateMaster;

public interface StateRepository extends JpaRepository<StateMaster, Long> {

	List<StateMaster> findByCountryMaster_CountryId(Long countryId);

}
