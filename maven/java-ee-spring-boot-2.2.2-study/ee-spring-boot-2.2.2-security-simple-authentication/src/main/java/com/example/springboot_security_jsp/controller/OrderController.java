package com.example.springboot_security_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "order-list";
    }
}
