package com.shine.model.stateMachine.model;

import com.shine.model.stateMachine.State;
import com.shine.model.stateMachine.model.IMario;
import com.shine.model.stateMachine.model.SmallMario;
import lombok.Data;

import static com.shine.model.stateMachine.State.*;

/**
 * @program: demo
 * @description: 状态模式
 * @author: bang.chen
 * @create: 2022-03-28 20:25
 **/
@Data
public class MarioStateMachine3 {
    private int score;
    private IMario currentState; // 不再使用枚举来表示状态

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = new SmallMario(this);
    }
    public void obtainMushRoom() {
        this.currentState.obtainMushRoom();
    }
    public void obtainCape() {
        this.currentState.obtainCape();
    }
    public void obtainFireFlower() {
        this.currentState.obtainFireFlower();
    }
    public void meetMonster() {
        this.currentState.meetMonster();
    }
}
