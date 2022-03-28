package com.shine.model.stateMachine;

/**
 * @program: demo
 * @description: 状态
 * @author: bang.chen
 * @create: 2022-03-28 20:24
 **/
public enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);
    private int value;

    private State(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}
