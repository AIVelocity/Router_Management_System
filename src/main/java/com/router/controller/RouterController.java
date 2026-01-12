package com.router.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.dto.RequestAddOrganization;
import com.router.dto.RequestAddRouter;
import com.router.service.RouterService;

@RestController
@RequestMapping("/router")
public class RouterController {
	@Autowired
	private RouterService routerService;

	@PostMapping("/add-router")
	// @PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> createRouter(
	        @RequestBody RequestAddRouter addRouterDetails) {

	  return routerService.createRouter(addRouterDetails);

	}
	
	@GetMapping("/router-list")
	// @PreAuthorize("@preAuthorizeSecurity.canAccessApplicationInfo()")
	public ResponseEntity<Map<String, Object>> getRouterList(
	        @RequestBody RequestAddRouter addRouterDetails) {

	  return routerService.getRouterList(addRouterDetails);

	}
	
	
}
