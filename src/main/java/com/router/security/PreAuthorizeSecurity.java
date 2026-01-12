package com.router.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.router.service.PreAuthorizeService;


@Component("preAuthorizeSecurity")
public class PreAuthorizeSecurity {
	
	
	@Autowired
	PreAuthorizeService preAuthorizeService;
	
	public boolean canAccessApplicationInfo() {
		return preAuthorizeService.isAccessAdmin()>=1 && preAuthorizeService.isAccessUsesCase()>=1;
	}
}
