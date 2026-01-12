package com.router.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.router.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="country_master")
public class CountryMaster extends Auditable{

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "country_id")
	    private Long countryId;

	    @Column(name = "country_name", nullable = false, length = 100)
	    private String countryName;
	    
	    @Column(name = "country_code", nullable = false, length = 10)
	    private String countryCode;

	    @OneToMany(mappedBy = "countryMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnore
	    private List<StateMaster> statesMaster;
}
