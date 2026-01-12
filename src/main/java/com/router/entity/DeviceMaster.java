package com.router.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "device_master")
@Data
public class DeviceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "device_name", nullable = false)
    private String deviceName;
}
