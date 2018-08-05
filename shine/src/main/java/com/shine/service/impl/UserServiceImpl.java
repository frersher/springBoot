package com.shine.service.impl;

import com.shine.basic.QueryParams;
import com.shine.basic.QueryParamsDeal;
import com.shine.mapper.UserInfoMapper;
import com.shine.model.UserInfo;
import com.shine.model.UserInfoExample;
import com.shine.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 用户服务实现
 * @author: bang.chen
 * @create: 2018-08-05 16:44
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public String deleteByPrimaryKey(Integer userId) {
        int result = userInfoMapper.deleteByPrimaryKey(userId);
        if(result<0){
            return "failure";
        }else{
            //TODO 可能有关连查询
            return "success";
        }
    }

    //根据id查所有liang
    public UserInfo selectByUserId(int id){
        return userInfoMapper.selectByPrimaryKey(id);
    }


    public String insert(UserInfo record) {
        // TODO Auto-generated method stub
        try {
            int i = userInfoMapper.insert(record);
            if (i != 1) {
                return "failure";
            }
            return "success";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "failure";
        }
    }

    @Override
    public int insertSelective(UserInfo record) {
        return 0;
    }


    //通过ID查找
    public UserInfo selectByPrimaryKey(Integer userId) {
        // TODO Auto-generated method stub
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    public UserInfo selectByTel(String tel) {
        List<UserInfo> list = userInfoMapper.selectByTel(tel);
        if (list != null) {
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    public UserInfo selectByMail(String mail) {
        List<UserInfo> list = userInfoMapper.selectByMail(mail);
        if (list != null) {
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }


    public String updateByPrimaryKeySelective(UserInfo record) {
        // TODO Auto-generated method stub
        int i = userInfoMapper.updateByPrimaryKeySelective(record);
        if (i != 1) {
            return "failure";
        }
        return "success";
    }

    public String updateByPrimaryKey(UserInfo record) {
        // TODO Auto-generated method stub
        int i = userInfoMapper.updateByPrimaryKey(record);
        if (i != 1) {
            return "failure";
        }
        return "success";
    }

    @Override
    public List<UserInfo> userListByCondition(QueryParams qp) {
        QueryParamsDeal qpd = new QueryParamsDeal(qp);
        UserInfoExample example = new UserInfoExample();
        example.setQueryParams(qp.AND + qpd.getParams());
        example.setOrderByClause(qp.getOrderByClause());
        example.setLimitByClause(qp.getPagesMysql());
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public int userListByConditionCount(QueryParams qp) {
        QueryParamsDeal qpd = new QueryParamsDeal(qp);
        UserInfoExample example = new UserInfoExample();
        example.setQueryParams(qp.AND + qpd.getParams());
        return userInfoMapper.countByExample(example);
    }


    public int update(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    public List<UserInfo> selectByUserInfo(UserInfo u) {
        // TODO Auto-generated method stub
        return userInfoMapper.selectByUserInfo(u);
    }

}
