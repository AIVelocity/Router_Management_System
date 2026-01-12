//package com.router.util;
//
//import tools.jackson.databind.JsonNode;
//import tools.jackson.databind.ObjectMapper;
//
//public class ValidateHierarchyJson {
//
//	public static void validateHierarchyJson(String hierarchyJson) {
//	    if (hierarchyJson == null || hierarchyJson.isBlank()) {
//	        throw new RuntimeException("Hierarchy JSON is required");
//	    }
//
//	    try {
//	        ObjectMapper mapper = new ObjectMapper();
//	        JsonNode root = mapper.readTree(hierarchyJson);
//
//	        if (!root.has("nodes") || !root.has("edges")) {
//	            throw new RuntimeException("Invalid hierarchy structure");
//	        }
//
//	    } catch (Exception e) {
//	        throw new RuntimeException("Invalid hierarchy JSON format");
//	    }
//	}
//
//}
