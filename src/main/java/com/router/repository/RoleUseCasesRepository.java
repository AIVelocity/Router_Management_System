package com.router.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.router.entity.RoleUsecases;

@Repository
public interface RoleUseCasesRepository extends JpaRepository<RoleUsecases, Long> {

//	Optional<RoleUsecases> findByRoleMasterRoleId(Long roleId);
	List<RoleUsecases> findByRoleMasterRoleId(Long roleId);

}
