package com.cc.app.pms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/res")
public class ResInfoController {

    @GetMapping("/find")
    public Object find(){
        return null;
    }
}
