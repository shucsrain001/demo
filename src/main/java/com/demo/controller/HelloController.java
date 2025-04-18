package com.demo.controller;

//import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

//    @Value("${sample.txt}")
    private String sample;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/get")
    public String get(){
        log.info("get request,return {}",sample);

        return sample;
    }
}