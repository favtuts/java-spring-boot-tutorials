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