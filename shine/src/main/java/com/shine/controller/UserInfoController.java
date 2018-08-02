package com.shine.controller;

import com.shine.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 *
 * @author wb-cb236432
 * @create 2018-08-02 15:08
 **/
@Controller
@RequestMapping("/user/*")
public class UserInfoController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginPost")
    @ResponseBody
    public String loginPost(@RequestBody User user){
        System.out.println(user.getUserNick());
        System.out.println(user.getPassword());
        return "index";
    }
}
