package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    Laptop laptop;
    public HelloController(Laptop laptop) {
        this.laptop = laptop;
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
    @GetMapping("sir")
    public String jaiho(){
        return "Hello, Spring Boot! again";
    }

    @GetMapping("lap")
    public void start(){
        laptop.compile();
    }
}
