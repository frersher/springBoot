package com.shine.basic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.shine.model.UserInfo;
import com.sun.deploy.security.TrustRecorder;

/**
 *缓存
 * @author wb-cb236432
 * @create 2018-08-06 15:20
 **/
public class DBCache {

    /**
     * K 用户名
     * V 用户信息
     */
    public static final Map<String, UserInfo> USERS_CACHE = new HashMap<>();
    /**
     * K 角色ID
     * V 权限编码
     */
    public static final Map<String, Collection<String>> PERMISSIONS_CACHE = new HashMap<>();

    static {
        // TODO 假设这是数据库记录
        USERS_CACHE.put("u1", new UserInfo(1, "123", "admin", "123", "123","admin",true));
        USERS_CACHE.put("u1", new UserInfo(2, "456", "test", "456", "456","test",false));

        PERMISSIONS_CACHE.put("admin", Arrays.asList("UserInfo:list", "UserInfo:add", "UserInfo:edit"));
        PERMISSIONS_CACHE.put("test", Collections.singletonList("UserInfo:list"));

    }
}