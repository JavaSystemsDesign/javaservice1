package com.example.javaservice1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    private static final Logger logger = LoggerFactory.getLogger(HealthService.class);

    public String getStatus() {
        logger.debug("HealthService.getStatus() called");
        return "OK";
    }
}
