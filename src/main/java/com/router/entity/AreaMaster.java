package com.router.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.router.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "area_master")
@Data
public class AreaMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private CityMaster cityMaster;

    @Column(name = "area_name", nullable = false, length = 100)
    private String areaName;

    @OneToMany(mappedBy = "areaMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganizationMaster> organizationMaster;
}
