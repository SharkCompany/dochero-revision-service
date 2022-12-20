package com.dochero.documentrevisionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document-revision")
public class TestController {

    @GetMapping("/test")
    public String string() {
        return "Testing ne";
    }
}
