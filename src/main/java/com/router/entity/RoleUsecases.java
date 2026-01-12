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
@Table(name = "role_usecases")
@Data
public class RoleUsecases extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleusecase_id")
    private Long roleUsecaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleMaster roleMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usecase_id", nullable = false)
    private UsecaseMaster usecaseMaster;
}
