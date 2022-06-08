package com.favtuts.config.bk;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    /*
     * Allow all requests
     * Ref: https://stackoverflow.com/questions/45232071/springboot-401-unauthorized-even-with-out-security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
}
