package com.router.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "router_device")
@Data
public class RouterDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_device_id")
    private Long routerDeviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "router_id", nullable = false)
    private RouterMaster router;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceMaster device;
}

