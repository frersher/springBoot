package com.shine.service.impl;

import com.shine.model.UserInfo;

/**
 * @author chenbang
 * @description AbstractInstanceService
 * @date 2022/1/11 4:30 下午
 */
public abstract class AbstractInstanceService<REQ extends UserInfo,DTO> {

    public DTO processInstance(REQ req){


        return null;
    }


    protected abstract DTO buildResult(REQ req);
}
