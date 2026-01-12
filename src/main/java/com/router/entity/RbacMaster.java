package com.router.entity;

import com.router.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rbac_master")
@Data
public class RbacMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rbac_id")
    private Long rbacId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleMaster roleMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usecase_id", nullable = false)
    private UsecaseMaster usecaseMaster;

    @Column(name = "read_access", length = 1)
    private String readAccess;   // Y / N

    @Column(name = "write_access", length = 1)
    private String writeAccess;

    @Column(name = "edit_access", length = 1)
    private String editAccess;

    @Column(name = "delete_access", length = 1)
    private String deleteAccess;
}
