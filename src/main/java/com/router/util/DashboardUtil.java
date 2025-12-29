package com.router.util;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DashboardUtil {
	
	public static String accessToken = "a7233e84f2584d6f3189cee6e091f96b";
//	public static void main(String[] args) {
//	System.out.println(getAllDeviceLocation());
//}
	public ResponseEntity<String> getDashboardSummary() {

	    
	   

	    
	    String url = "https://api2.ic.peplink.com/rest/o/l0epug/networks"
	            + "?caller_ref=2025122910225455083"
	            + "&is_show_detail=true";

	    
	    RestTemplate restTemplate = new RestTemplate();

	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(accessToken);
	    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

	    
	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	    
	    return restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            requestEntity,
	            String.class
	    );
		}


	public static ResponseEntity<String> getAllDeviceLocation() {
		
		 String url = "https://api.ic.peplink.com/rest/o/l0epug/g/5/loc";
		    
		    RestTemplate restTemplate = new RestTemplate();

		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.setBearerAuth(accessToken);
		    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

		    
		    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		    return restTemplate.exchange(
		            url,
		            HttpMethod.GET,
		            requestEntity,
		            String.class
		    );
	}
	
	
		
}
