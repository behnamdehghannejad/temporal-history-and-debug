package com.example.temporalhistoryanddebug;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping("/test")
    public String getResult() {
        return service.start();
    }
}
