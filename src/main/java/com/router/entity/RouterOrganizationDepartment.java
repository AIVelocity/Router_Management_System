package com.router.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "router_organization_dept")
@Data
public class RouterOrganizationDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_organization_dept_id")
    private Long routerOrganizationDeptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private DepartmentMaster departmentMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "router_id", nullable = false)
    private RouterMaster routerMaster;
}
