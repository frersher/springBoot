package com.shine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shine.basic.Filter;
import com.shine.basic.rep.UserQuery;
import com.shine.basic.rsp.PageResponse;
import com.shine.model.UserInfo;
import com.shine.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 *
 * @author wb-cb236432
 * @create 2018-08-02 15:08
 **/
@Controller
public class UserInfoController {
    private static final Logger logger = LogManager.getLogger(UserInfoController.class);
    @Resource
    private UserService userService;

    @GetMapping(value = "/hello")
    public String hello() {
        logger.info("不登录也可以访问...");
        return "hello...";
    }

    @GetMapping(value = "/index")
    public String index() {
        logger.info("登陆成功了...");
        return "index";
    }

    @GetMapping(value = "/denied")
    public String denied() {
        logger.info("小伙子权限不足,别无谓挣扎了...");
        return "denied...";
    }



    @GetMapping("/login")
    public String login(String username,String password) {
        return "login";
    }


    @RequestMapping("/loginPost")
    public String loginPost(UserQuery query,Model model) {
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
        Subject sub = SecurityUtils.getSubject();
        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
        // 认证执行者交由 com.battcn.config.AuthRealm 中 doGetAuthenticationInfo 处理
        // 当以上认证成功后会向下执行,认证失败会抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(query.getUserNick(), query.getUserPassword());
        try {
            sub.login(token);
        } catch (UnknownAccountException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,用户不存在", query.getUserNick());
            token.clear();
            return "UnknownAccountException";
        } catch (LockedAccountException lae) {
            logger.error("对用户[{}]进行登录验证,验证未通过,账户已锁定", query.getUserNick());
            token.clear();
            return "LockedAccountException";
        } catch (ExcessiveAttemptsException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", query.getUserNick());
            token.clear();
            return "ExcessiveAttemptsException";
        } catch (AuthenticationException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", query.getUserNick(), e);
            token.clear();
            return "AuthenticationException";
        }
        return "index";
    }

    @RequestMapping("/queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(UserQuery query) {
        System.out.println(String.format("pageNum = %s,pageSize = %s",query.getPageNum(),query.getPageSize()));

        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Page<UserInfo> userList = (Page<UserInfo>)userService.userListByCondition(query);
        Map resultMap = new HashMap<String, Object>();

        resultMap.put("data", userList.getResult());
        //页数信息配置
        resultMap.put("limit", userList.getPageSize());
        resultMap.put("page", userList.getPageSize());
        resultMap.put("total",userList.getTotal());
       // resultMap.put("iTotalDisplayRecords",userList.getTotal());
        return resultMap;
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
