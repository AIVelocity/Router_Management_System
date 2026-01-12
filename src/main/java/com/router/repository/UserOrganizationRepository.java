package com.router.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.router.entity.UserOrganization;


public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {

    Optional<UserOrganization> findByUserMasterUserId(Long userId);

}
