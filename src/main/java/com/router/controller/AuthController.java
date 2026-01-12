package com.router.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.entity.RbacMaster;
import com.router.entity.UserMaster;
import com.router.entity.UserOrganization;
import com.router.jwtutil.JwtUtil;
import com.router.repository.RbacMasterRepository;
import com.router.repository.RoleUseCasesRepository;
import com.router.repository.UserMasterRepository;
import com.router.repository.UserOrganizationRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserMasterRepository userMasterRepository;
    @Autowired private UserOrganizationRepository userOrganizationRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private RoleUseCasesRepository roleUseCasesRepository;
    @Autowired private RbacMasterRepository rbacMasterRepository;
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {

        // 1️⃣ Validate user
        UserMaster user = userMasterRepository.findByUsername(req.get("username"))
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(req.get("password"), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 2️⃣ Fetch UserOrganization (Org + Role + Dept)
        UserOrganization userOrg = userOrganizationRepository
                .findByUserMasterUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not mapped to organization"));
        Long roleId = userOrg.getRoleMaster().getRoleId();
        
        Long organizationId = userOrg.getOrganizationMaster() != null
                ? userOrg.getOrganizationMaster().getOrgId()
                : null;
        
        Long deptId = userOrg.getDepartmentMaster() != null
                ? userOrg.getDepartmentMaster().getDeptId()
                : null;

        Map<String, List<String>> usecasePermissions = new HashMap<>();

        List<RbacMaster> rbacList =
                rbacMasterRepository.findByRoleMasterRoleId(roleId);
        for (RbacMaster rbac : rbacList) {

            List<String> permissions = new ArrayList<>();

        if ("Y".equalsIgnoreCase(rbac.getReadAccess())) permissions.add("READ");
        if ("Y".equalsIgnoreCase(rbac.getWriteAccess())) permissions.add("WRITE");
        if ("Y".equalsIgnoreCase(rbac.getEditAccess())) permissions.add("EDIT");
        if ("Y".equalsIgnoreCase(rbac.getDeleteAccess())) permissions.add("DELETE");


            usecasePermissions.put(
                rbac.getUsecaseMaster().getUsecaseName(),
                permissions
            );
        }


        // 4️⃣ Build JWT claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("organizationId", organizationId);
        claims.put("roleId", roleId);
        claims.put("roleName", userOrg.getRoleMaster().getRoleName());
        claims.put("deptId", deptId);
        claims.put("usecases", usecasePermissions);// Addrouer-read,write,update,delete

        // 5️⃣ Generate token
        String token = jwtUtil.generateToken(user.getUsername(), claims);

        return Map.of(
                "token", token
               // "role", userOrg.getRoleMaster().getName(),
               // "organizationId", organizationId,
               // "usecases", usecasePermissions
        );
    }




}
