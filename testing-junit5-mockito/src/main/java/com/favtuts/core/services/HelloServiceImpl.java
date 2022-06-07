package com.favtuts.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favtuts.core.repository.HelloRepository;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    HelloRepository helloRepository;

    @Override
    public String get() {
        return helloRepository.get();
    }
    
}
