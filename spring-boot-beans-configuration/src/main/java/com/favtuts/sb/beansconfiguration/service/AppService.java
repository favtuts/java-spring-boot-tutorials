package com.favtuts.sb.beansconfiguration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final MultiplierService multiplierService;

    public AppService(@Autowired MultiplierService multiplierService) {
        this.multiplierService = multiplierService;
    }

    public int calculate(int aValue) {
        return multiplierService.multiply(aValue);
    }
}
