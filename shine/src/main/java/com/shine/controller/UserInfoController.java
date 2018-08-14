package com.shine.controller;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shine.basic.rep.UserQuery;
import com.shine.basic.rsp.PageResponse;
import com.shine.model.UserInfo;
import com.shine.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 *
 * @author wb-cb236432
 * @create 2018-08-02 15:08
 **/
@Controller
@RequestMapping("/user/*")
public class UserInfoController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    } 


    @RequestMapping("/loginPost")
    public String loginPost(UserQuery query,Model model) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Page<UserInfo> userList = (Page<UserInfo>)userService.userListByCondition(query);
        model.addAttribute("allUsers", userList.getResult());
        model.addAttribute("pageSize",userList.getPageSize());
        model.addAttribute("total",userList.getTotal());
        return "index";
    }


    @RequestMapping("/queryPageUser")
    public  PageResponse<List<UserInfo>>  queryPageUser(UserQuery query){
        PageResponse<List<UserInfo>> response = new PageResponse();
        UserQuery userQuery = new UserQuery();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Page<UserInfo> userList = (Page<UserInfo>)userService.userListByCondition(userQuery);
        response.setCount(userList.getTotal());
        response.setPageNum(userList.getPageNum());
        response.setPages(userList.getPages());
        response.setData(userList.getResult());
        return  response;
    }

}
