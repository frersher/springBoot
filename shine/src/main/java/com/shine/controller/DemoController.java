package com.shine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * @author wb-cb236432
 * @create 2018-06-22 10:09
 **/
@RestController
public class DemoController {
    @RequestMapping("/index")
    public String index(){
      return "hello world";
    }
}
