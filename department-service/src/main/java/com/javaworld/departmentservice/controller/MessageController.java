package com.javaworld.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope - This annotation will force the spring bean to reload the configuration. This will
 * trigger the reload event for this spring bean
 */
@RestController
@RefreshScope
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;
    @GetMapping("message")
    public String getMessage(){
        return message;
    }
}
