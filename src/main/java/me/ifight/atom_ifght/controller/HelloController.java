package me.ifight.atom_ifght.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/tjliu/hello")
    public String hello(){
        return "Hello from tjliu";
    }
}
