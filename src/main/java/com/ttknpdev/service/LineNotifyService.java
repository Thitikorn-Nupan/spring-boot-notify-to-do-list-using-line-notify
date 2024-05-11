package com.ttknpdev.service;

import com.ttknpdev.logging.Logback;
import com.ttknpdev.repository.LineNotifyRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class LineNotifyService implements LineNotifyRepo {

    // **** Remember you can not access Properties if you are not injected by @Autowrite this Bean (LineNotifyRepo,LineNotifyService)
    @Value(value = "${line.notify.url}")
    private String lineNotifyUrl;
    @Value(value = "${line.notify.token}")
    private String lineNotifyToken;

    // initial in constructor
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private Logback logback;

    // No HttpMessageConverter for java.util.LinkedHashMap,Map,... and content type "application/x-www-form-urlencoded"
    // MultiValueMap use it. it's the best way
    private HttpEntity<MultiValueMap<String, Object>> httpEntity;
    private MultiValueMap<String, Object> map;

    public LineNotifyService() {
        restTemplate = new RestTemplate();
        logback = new Logback(LineNotifyService.class);
        map = new LinkedMultiValueMap<>();
    }

    @Override
    public LinkedHashMap<String, Object> sendLineNotifyMessageAndSticker(String message, int stickerPackageId, int stickerId) throws Exception {
        map.clear(); // if not clear one element has more values
        map.add("message", message);
        map.add("stickerPackageId", stickerPackageId);
        map.add("stickerId", stickerId);
        return callLineNotifyByMultiValueMap(map);
    }


    private LinkedHashMap<String, Object> callLineNotifyByMultiValueMap(MultiValueMap<String, Object> map) throws Exception {

        // **** have to create new object when use method Always
        headers = new HttpHeaders();

        logback.log.info("callLineNotifyByMultiValueMap(MultiValueMap) method is working");
        logback.log.info("map : {}", map); // map : {message=hello}

        // for application/ x-www-form-urlencoded. Or You can set like headers.add("Content..","")
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + lineNotifyToken);

        httpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(lineNotifyUrl, HttpMethod.POST, httpEntity, LinkedHashMap.class);

        // <200 OK OK,{status=200, message=ok},[Server:"nginx", Date:"Thu, 09 May 2024 06:15:35 GMT", Content-Type:"application/json", Transfer-Encoding:"chunked", Keep-Alive:"timeout=9", Vary:"Accept-Encoding", X-RateLimit-Limit:"1000", X-RateLimit-ImageLimit:"50", X-RateLimit-Remaining:"974", X-RateLimit-ImageRemaining:"50", X-RateLimit-Reset:"1715236089", X-Robots-Tag:"noindex, nofollow, nosnippet, noarchive"]>
        logback.log.info("response : {}", response);

        return response.getBody();

    }
}
