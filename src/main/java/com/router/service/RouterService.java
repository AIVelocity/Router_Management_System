package com.router.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.router.dto.RequestAddRouter;

public interface RouterService {

	ResponseEntity<Map<String, Object>> createRouter(RequestAddRouter addRouterDetails);

	ResponseEntity<Map<String, Object>> getRouterList(RequestAddRouter addRouterDetails);

}
