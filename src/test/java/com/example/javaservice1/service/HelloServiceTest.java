package com.example.javaservice1.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloServiceTest {

    private final HelloService helloService = new HelloService();

    @Test
    void getGreeting_returnsHello() {
        assertEquals("Hello", helloService.getGreeting());
    }
}
