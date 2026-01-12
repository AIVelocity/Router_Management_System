package com.router.dto;

import lombok.Data;

@Data
public class RequestAddRouter {
	
	private String routerSerialNumber;
	
	private String routerName; 
	
	private Long deptId;
	
	private Long routerId;
	
	private Long orgId;
}
