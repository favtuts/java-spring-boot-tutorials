package com.favtuts.sbrequestfilterdemo;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/demo")
@Slf4j
public class DemoController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> handle(@RequestBody String body) {
        log.info(body);
        return Map.of("requestBody", body, "timestamp", System.currentTimeMillis() + "", "success", "true");
    }
}