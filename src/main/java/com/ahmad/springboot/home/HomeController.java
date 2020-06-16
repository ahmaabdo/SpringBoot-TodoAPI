package com.ahmad.springboot.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Spring will know that this class will handle APIs (Web Services) from the below annotation
@RestController
public class HomeController {

    @RequestMapping(value = "/" )
    public String greeting() {
        return "Hello, Welcome to SpringBoot";
    }


     @GetMapping(value = "/{name}")
    public String greetingWithName(@PathVariable String name){
        return String.format("Welcome %s to our SpringBoot App", name);
    }
}
