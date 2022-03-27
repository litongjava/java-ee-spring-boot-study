package com.example.springboot_security_jsp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author john
 * @date 2020/1/11 - 19:07
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/findAll")
    @Secured("ROLE_PRODUCT")
    public String hello() {
        return "product-list";
    }
}
