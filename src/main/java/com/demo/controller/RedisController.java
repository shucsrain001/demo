package com.demo.controller;


import com.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisController {

//    @Autowired
    private RedisUtils redisUtils;

    // 保存数据
    @PostMapping("/save")
    public String save(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisUtils.save(key, value);
        return "Data saved successfully!";
    }

    // 获取数据
    @GetMapping("/get")
    public Object get(@RequestParam("key") String key) {
        Object value = redisUtils.get(key);
        return value != null ? value : "Key not found";
    }

    // 删除数据
    @DeleteMapping("/delete")
    public String delete(@RequestParam("key") String key) {
        redisUtils.delete(key);
        return "Data deleted successfully!";
    }
}
