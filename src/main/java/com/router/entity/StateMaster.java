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
@Table(name = "state_master")
@Data
public class StateMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long stateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonIgnore
    private CountryMaster countryMaster;

    @Column(name = "state_name", nullable = false, length = 100)
    private String stateName;

    @OneToMany(mappedBy = "stateMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DistrictMaster> districtMaster;
}
