package com.shine.service.impl;

import com.shine.basic.rep.UserQuery;
import com.shine.dao.UserInfoDao;
import com.shine.model.UserInfo;
import com.shine.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description: 用户服务实现
 * @author: bang.chen
 * @create: 2018-08-05 16:44
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public String deleteByPrimaryKey(Integer userId) {
        int result = userInfoDao.deleteByPrimaryKey(userId);
        if(result<0){
            return "failure";
        }else{
            //TODO 可能有关连查询
            return "success";
        }
    }

    //根据id查所有liang
    public UserInfo selectByUserId(int id){
        return userInfoDao.selectByPrimaryKey(id);
    }


    public String insert(UserInfo record) {
        // TODO Auto-generated method stub
        try {
            int i = userInfoDao.insert(record);
            if (i != 1) {
                return "failure";
            }
            return "success";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "failure";
        }
    }


    //通过ID查找
    public UserInfo selectByPrimaryKey(Integer userId) {
        // TODO Auto-generated method stub
        return userInfoDao.selectByPrimaryKey(userId);
    }

    public UserInfo selectByTel(String tel) {
        List<UserInfo> list = userInfoDao.selectByTel(tel);
        if (list != null) {
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    public UserInfo selectByMail(String mail) {
        List<UserInfo> list = userInfoDao.selectByMail(mail);
        if (list != null) {
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }


    @Override
    public String updateByPrimaryKey(UserInfo record) {
        // TODO Auto-generated method stub
        int i = userInfoDao.updateByPrimaryKey(record);
        if (i != 1) {
            return "failure";
        }
        return "success";
    }

    @Override
    public List<UserInfo> userListByCondition(UserQuery userQuery) {
        return userInfoDao.selectByExample(userQuery);
    }

    @Override
    public int userListByConditionCount(UserQuery userQuery) {
        return userInfoDao.countByExample(userQuery);
    }

    @Override
    public List<UserInfo> selectByUserInfo(UserInfo u) {
        // TODO Auto-generated method stub
        return userInfoDao.selectByUserInfo(u);
    }

}
