package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 无_言
 * demo3.html的controller
 * */

@Controller
@RequestMapping("demo3")
public class demo3 {

    @RequestMapping("/demo3")
    public String demo3(){
        System.out.println("---------------------------000000000 ----");
        return "demo3";//地址指向demo3.html
    }

}