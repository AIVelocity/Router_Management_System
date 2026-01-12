package com.router.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.router.dto.UsecasePermission;
import com.router.entity.UsecaseMaster;

@Repository
public interface UsecaseRepository extends JpaRepository<UsecaseMaster, Long> {

    @Query(
        value = """
            select u.usecase_name,
    rm.read_access,
    rm.write_access,
    rm.edit_access,
    rm.delete_access from usecase_master u left join  rbac_master  rm on u.usecase_id=rm.usecase_id left join user_role ur on rm.role_id=ur.role_id
where ur.user_id = :userId
            """,
        nativeQuery = true
    )
    List<UsecasePermission> findUsecasesByUserId(@Param("userId") Long userId);
}

