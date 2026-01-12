package com.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Long> {

}
