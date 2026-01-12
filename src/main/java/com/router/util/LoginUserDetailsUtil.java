package com.router.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class LoginUserDetailsUtil {
	
	public static Long getUserId() {
        Long userId = null;
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
              //  System.out.println("------------------------------------>>"+request.getAttribute("userId"));
                Object userIdObj = request.getAttribute("userId");

                Long userIdAttr = ((Number) userIdObj).longValue();
               // Long userIdAttr = (Long) request.getAttribute("userId");
                System.out.println("------"+userIdAttr);
                if (userIdAttr != null) {
                	userId =userIdAttr;
                } else {
                    log.warn("UserID attribute is not available in the request.");
                }
            } else {
                log.warn("RequestContextHolder returned null, no active request context.");
            }
        } catch (Exception e) {
            log.error("Exception while fetching UserID from request: {}", e.getMessage(), e);
        }
        return userId;
    }
	
	
	public static Map<String, Map<String, List<String>>> getRoleUseCases() {

	    Map<String, Map<String, List<String>>> roleBaseUseCases = new HashMap<>();

	    try {
	        ServletRequestAttributes attrs =
	                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

	        if (attrs == null) {
	            log.warn("No active request context");
	            return roleBaseUseCases;
	        }

	        HttpServletRequest request = attrs.getRequest();

//	        List<String> roles =
//	                (List<String>) request.getAttribute("roles");
	        
	        String role =
	                 (String) request.getAttribute("roleName");

	        Map<String, List<String>> usecases =
	                (Map<String, List<String>>) request.getAttribute("usecases");
	        
	        Map<String, List<String>> usecasePermissionMap = new HashMap<>();
	        
	        for (Map.Entry<String, List<String>> entry : usecases.entrySet()) {

	            String usecase = entry.getKey();

	            List<String> permissions = entry.getValue()
	                    .stream()
	                    .map(String::valueOf)
	                    .toList();

	            usecasePermissionMap.put(usecase, permissions);
	        }

	        // 3️⃣ Final structure
	        roleBaseUseCases.put(role, usecasePermissionMap);

	    

//	        if (role != null) {
//	        //	List<String> asList = Arrays.asList(roles);
//	        	roleBaseUseCases.put("roles", role);
//	        } else {
//	            log.warn("Roles attribute not found in request");
//	        }
//
//	        if (usecases != null) {
//	            map.put("usecases", usecases);
//	        } else {
//	            log.warn("Usecases attribute not found in request");
//	        }

	    } catch (Exception e) {
	        log.error("Error while reading roles/usecases from request attributes", e);
	    }

	    return roleBaseUseCases;
	}

	
}
