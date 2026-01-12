package com.router.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

	 Optional<UserMaster> findByUsername(String username);

}
