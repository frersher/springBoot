package com.shine.service;

import com.shine.basic.QueryParams;
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

    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Integer userId);

    UserInfo selectByTel(String tel);

    UserInfo selectByMail(String mail);

    String updateByPrimaryKeySelective(UserInfo record);

    String updateByPrimaryKey(UserInfo record);

    List<UserInfo> userListByCondition(QueryParams qp);

    int userListByConditionCount(QueryParams qp);


    int update(UserInfo record);

    List<UserInfo> selectByUserInfo(UserInfo u);

    //liang
    UserInfo selectByUserId(int id);


}
