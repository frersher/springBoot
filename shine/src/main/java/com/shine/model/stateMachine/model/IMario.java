package com.shine.model.stateMachine.model;

import com.shine.model.stateMachine.State;

/**
 * @program: demo
 * @description: 所有状态接口
 * @author: bang.chen
 * @create: 2022-03-28 21:03
 **/
public interface IMario {
    State getName();
    //以下是定义的事件

    /**
     * 获得蘑菇
     */
    void obtainMushRoom();

    /**
     * 获得披风
     */
    void obtainCape();

    /**
     * 获得火圈
     */
    void obtainFireFlower();

    /**
     * 遇见怪物
     */
    void meetMonster();
}
