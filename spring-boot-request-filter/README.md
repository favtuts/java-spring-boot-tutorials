# How to Record Request and Response Bodies in Sping Boot Applications
* https://tuts.heomi.net/how-to-record-request-and-response-bodies-in-sping-boot-applications/

# The case of HttpMessageNotReadableException

As you know, streams can only be read once. If we read the request body first in order to log it, then spring’s `HttpMessageConverter` will throw an exception when it reads the request body again: `org.springframework.http.converter. HttpMessageNotReadableException: Required request body is missing: ....`

Here is the filter for logging:
```java
package com.favtuts.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@WebFilter(urlPatterns = "/*")
@Order(-999)
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        byte[] requestBody = StreamUtils.copyToByteArray(request.getInputStream());

        log.info("request body = {}", new String(requestBody, StandardCharsets.UTF_8));

        filterChain.doFilter(request, response);
    }
}
```

# Re-readable streams

The stream itself cannot be read repeatedly. But you can make a copy of it when you read it, and then you can read it multiple times. Luckily, spring provides this tool class.

* `ContentCachingRequestWrapper`
* `ContentCachingResponseWrapper`

Modify the `AccessLogFilter`:
```java
package com.favtuts.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Component
@WebFilter(urlPatterns = "/*")
@Order(-999)
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

        // Execution request chain
        filterChain.doFilter(req, resp);
        
        // Get Cache
        byte[] requestBody = req.getContentAsByteArray();
        byte[] responseBody = resp.getContentAsByteArray();
        
        log.info("request body = {}", new String(requestBody, StandardCharsets.UTF_8));
        
        log.info("response body = {}", new String(responseBody, StandardCharsets.UTF_8));
        
        // Finally remember to respond to the client with the cached data.
        resp.copyBodyToResponse();
    }
}
```

# Testing

Request
```bash
curl --location 'http://localhost:8080/api/demo' \
--header 'Content-Type: text/plain' \
--header 'Cookie: JSESSIONID=07A59A43F49641E48301A2DE42CA7B8C' \
--data 'This is the demo of request body in text plain value'
```

Response
```json
{
    "success": "true",
    "requestBody": "This is the demo of request body in text plain value",
    "timestamp": "1733671889840"
}
```

Log:
```
2024-12-08T22:31:29.840+07:00  INFO 22472 --- [sbrequestfilterdemo] [nio-8080-exec-1] c.f.sbrequestfilterdemo.DemoController   : This is the demo of request body in text plain value
2024-12-08T22:31:29.889+07:00  INFO 22472 --- [sbrequestfilterdemo] [nio-8080-exec-1] c.f.sbrequestfilterdemo.AccessLogFilter  : request body = This is the demo of request body in text plain value
2024-12-08T22:31:29.891+07:00  INFO 22472 --- [sbrequestfilterdemo] [nio-8080-exec-1] c.f.sbrequestfilterdemo.AccessLogFilter  : response body = {"success":"true","requestBody":"This is the demo of request body in text plain value","timestamp":"1733671889840"}
```