package com.shine.mapper;

import com.shine.model.UserInfo;
import com.shine.model.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer userId);


    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 登录验证(tel,mail,empid,idcard)4种验证
     *
     */
    List<UserInfo> selectByTel(String tel);

    List<UserInfo> selectByMail(String mail);

    List<UserInfo> selectByUserInfo(UserInfo users);

    //liang
    UserInfo selectByPrimaryKey(int id);

}