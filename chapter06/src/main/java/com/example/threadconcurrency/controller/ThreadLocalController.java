package com.example.threadconcurrency.controller;

import com.example.threadconcurrency.threadlocal.RequestHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadlocal")
public class ThreadLocalController {


    @RequestMapping("/test")
    public Long test(){
        return RequestHolder.getId();
    }
}
