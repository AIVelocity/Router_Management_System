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
@Table(name = "city_master")
@Data
public class CityMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private DistrictMaster districtMaster;

    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;

    @OneToMany(mappedBy = "cityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AreaMaster> areas;
}
