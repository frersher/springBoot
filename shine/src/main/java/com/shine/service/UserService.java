package com.shine.service;

import com.shine.basic.QueryParams;
import com.shine.basic.rep.UserQuery;
import com.shine.model.UserInfo;

import java.util.List;

/**
 * @program: demo
 * @description: 用户服务
 * @author: bang.chen
 * @create: 2018-08-05 16:04
 **/
public interface UserService {
    String deleteByPrimaryKey(Integer userId);

    String insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    UserInfo selectByTel(String tel);

    UserInfo selectByMail(String mail);

    String updateByPrimaryKey(UserInfo record);

    List<UserInfo> userListByCondition(UserQuery userQuery);

    int userListByConditionCount(UserQuery userQuery);

    List<UserInfo> selectByUserInfo(UserInfo u);

    //liang
    UserInfo selectByUserId(int id);


}
