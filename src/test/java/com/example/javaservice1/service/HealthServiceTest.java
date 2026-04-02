package com.example.javaservice1.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthServiceTest {

    private final HealthService healthService = new HealthService();

    @Test
    void getStatus_returnsOk() {
        assertEquals("OK", healthService.getStatus());
    }
}
