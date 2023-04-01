package com.uairobot.bill.study.spring.boot.devtools;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("version")
public class VersionController {

    public String index() {
        return "v1.0.0";
    }
}
