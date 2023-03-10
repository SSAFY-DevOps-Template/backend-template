package com.example.deploytestback.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Hello API", tags = {"hello"})
@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "μΈμ¬", notes = "μλ?")
    public String hello() {
        return "Hello, Spring Deploy";
    }

}
