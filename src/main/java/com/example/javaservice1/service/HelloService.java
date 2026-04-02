package com.example.javaservice1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    public String getGreeting() {
        logger.debug("HelloService.getGreeting() called");
        return "Hello";
    }
}
