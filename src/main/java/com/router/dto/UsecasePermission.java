package com.router.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsecasePermission {
	
	private String usecaseName;
	
	private String readAccess;
	
	private String writeAccess;
	
	private String editAccess;
	
	private String deleteAccess;

}
