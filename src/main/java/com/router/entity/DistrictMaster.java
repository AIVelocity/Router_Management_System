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
@Table(name = "district_master")
@Data
public class DistrictMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    @JsonIgnore
    private StateMaster stateMaster;

    @Column(name = "district_name", nullable = false, length = 100)
    private String districtName;

    @OneToMany(mappedBy = "districtMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CityMaster> cities;
}
