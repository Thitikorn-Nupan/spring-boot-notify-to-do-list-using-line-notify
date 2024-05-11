package com.ttknpdev.resttemplate;

import com.ttknpdev.logging.Logback;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RequestRenderServiceServer {

    private RestTemplate restTemplate ;
    private HttpHeaders headers;
    private final String URL = "https://to-do-list-75i2.onrender.com/server";
    private Logback logback;

    public RequestRenderServiceServer() {
        restTemplate = new RestTemplate();
        logback = new Logback(RequestRenderServiceServer.class);

    }
    public void requestRenderServer() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity< String > entity = new HttpEntity<>(headers);
        ResponseEntity< String > response = restTemplate.exchange(URL , HttpMethod.GET , entity , String.class);
        // System.out.println(address);
        logback.log.info(response.getBody());
    }
}
