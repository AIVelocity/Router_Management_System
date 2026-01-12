package com.router.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="router_master")
public class RouterMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_id")
    private Long routerId;

    @Column(name = "router_serial_number", nullable = false, unique = true, length = 100)
    private String routerSerialNumber;

    @Column(name = "router_name", nullable = false, length = 100)
    private String routerName;

   
}