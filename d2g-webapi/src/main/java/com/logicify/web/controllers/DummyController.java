package com.logicify.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author knorr
 */
@RestController
public class DummyController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
