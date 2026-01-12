package com.router.dto;

import lombok.Data;
import tools.jackson.databind.JsonNode;

@Data
public class RequestAddOrganization {

    private String organizationName;

    private Long countryId;

    private Long stateId;

    private Long districtId;

    private String cityName;

    private String areaName;
    
    private JsonNode hierarchyJson;
}

