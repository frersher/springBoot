package com.shine.dao;

import com.shine.basic.rep.UserQuery;
import com.shine.model.UserInfo;

import java.util.List;

public interface UserInfoDao {
    int countByExample(UserQuery userQuery);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    List<UserInfo> selectByExample(UserQuery userQuery);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectByTel(String tel);

    List<UserInfo> selectByMail(String mail);

    List<UserInfo> selectByUserInfo(UserInfo users);

    UserInfo selectByPrimaryKey(Integer userId);

}