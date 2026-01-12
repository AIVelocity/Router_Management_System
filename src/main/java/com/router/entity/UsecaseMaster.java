package com.router.entity;

import com.router.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usecase_master")
public class UsecaseMaster extends Auditable  {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "usecase_id")
	    private Long usecaseId;

	    @Column(name = "usecase_name", nullable = false, unique = true)
	    private String usecaseName;
}
