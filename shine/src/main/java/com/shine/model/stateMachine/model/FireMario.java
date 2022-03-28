package com.shine.model.stateMachine.model;

import com.shine.model.stateMachine.State;

/**
 * @program: demo
 * @description:
 * @author: bang.chen
 * @create: 2022-03-28 21:12
 **/
public class FireMario implements IMario{

    private MarioStateMachine3 marioStateMachine;

    public FireMario(MarioStateMachine3 marioStateMachine){
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom() {
    }

    @Override
    public void obtainCape() {

    }

    @Override
    public void obtainFireFlower() {

    }

    @Override
    public void meetMonster() {
        marioStateMachine.setCurrentState(new SmallMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() - 300);
    }
}
