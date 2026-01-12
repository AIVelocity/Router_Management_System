package com.router.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.router.dto.UsecasePermission;
import com.router.entity.RbacMaster;
import com.router.entity.UserOrganization;
import com.router.repository.RbacMasterRepository;
import com.router.repository.UsecaseRepository;
import com.router.repository.UserOrganizationRepository;
import com.router.service.PreAuthorizeService;
import com.router.util.LoginUserDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PreAuthorizeServiceImpl implements PreAuthorizeService {

	private final LoginUserDetailsUtil loginUserDetailsUtil;

	PreAuthorizeServiceImpl(LoginUserDetailsUtil loginUserDetailsUtil) {
		this.loginUserDetailsUtil = loginUserDetailsUtil;
	}

	@Autowired
	private RbacMasterRepository rbacMasterRepository;

	@Autowired
	private UsecaseRepository UsecaseRepository;

	@Autowired
	private UserOrganizationRepository userOrganizationRepository;

	@Override
	public int isAccessAdmin() {

		try {
			Long userId = LoginUserDetailsUtil.getUserId();
			System.out.println("===>id" + userId);
			Map<String, Map<String, List<String>>> roleUseCases = LoginUserDetailsUtil.getRoleUseCases();

			String roleNameFromToken = roleUseCases.keySet().iterator().next();

			UserOrganization userOrg = userOrganizationRepository.findById(userId).orElseThrow();

			String roleNameFromDb = userOrg.getRoleMaster().getRoleName();

			return roleNameFromToken.equalsIgnoreCase(roleNameFromDb) ? 1 : 0;

		} catch (Exception e) {
			log.error("Unexpected error during access validation", e);
			return 0;
		}
	}

	@Override
	public int isAccessUsesCase() {

		try {
			Long userId = LoginUserDetailsUtil.getUserId();
			Map<String, Map<String, List<String>>> roleUseCases = LoginUserDetailsUtil.getRoleUseCases();

			Map<String, List<String>> roleUseCasesFromToken = Collections.emptyMap();

			if (roleUseCases != null && !roleUseCases.isEmpty()) {
				roleUseCasesFromToken = roleUseCases.values().iterator().next();
			}

			UserOrganization userOrg = userOrganizationRepository.findById(userId).orElseThrow();
			Long roleId = userOrg.getRoleMaster().getRoleId();

			List<RbacMaster> rbacList = rbacMasterRepository.findByRoleMasterRoleId(roleId);

			if (rbacList.isEmpty()) {
				throw new RuntimeException("No RBAC mapping found for roleId " + roleId);
			}

			Map<String, List<String>> roleUseCasesFromDb = new HashMap<>();

			for (RbacMaster rbac : rbacList) {

				String usecaseName = rbac.getUsecaseMaster().getUsecaseName();

				List<String> permissions = new ArrayList<>();

//			if (Boolean.TRUE.equals(rbac.getReadAccess())) {
//				permissions.add("READ");
//			}
//			if (Boolean.TRUE.equals(rbac.getWriteAccess())) {
//				permissions.add("WRITE");
//			}
//			if (Boolean.TRUE.equals(rbac.getEditAccess())) {
//				permissions.add("EDIT");
//			}
//			if (Boolean.TRUE.equals(rbac.getDeleteAccess())) {
//				permissions.add("DELETE");
//			}

				if ("Y".equals(rbac.getReadAccess())) {
					permissions.add("READ");
				}
				if ("Y".equals(rbac.getWriteAccess())) {
					permissions.add("WRITE");
				}
				if ("Y".equals(rbac.getEditAccess())) {
					permissions.add("EDIT");
				}
				if ("Y".equals(rbac.getDeleteAccess())) {
					permissions.add("DELETE");
				}

				if (!permissions.isEmpty()) {
					roleUseCasesFromDb.put(usecaseName, permissions);
				}
			}

			boolean isEqual = normalize(roleUseCasesFromToken).equals(normalize(roleUseCasesFromDb));

			return isEqual ? 1 : 0;

		} catch (Exception e) {
			log.error("Error during usecase access validation", e);
			return 0;
		}
	}

	private Map<String, Set<String>> normalize(Map<String, List<String>> source) {

		Map<String, Set<String>> normalized = new HashMap<>();

		if (source == null)
			return normalized;

		source.forEach((usecase, permissions) -> {

			Set<String> permSet = permissions == null ? Collections.emptySet()
					: permissions.stream().map(String::toUpperCase).collect(Collectors.toSet());

			normalized.put(usecase.toUpperCase(), permSet);
		});

		return normalized;
	}

}
