package com.shine.model.stateMachine.model;

import com.shine.model.stateMachine.State;

/**
 * @program: demo
 * @description:
 * @author: bang.chen
 * @create: 2022-03-28 21:12
 **/
public class CapeMario implements IMario{

    private MarioStateMachine3 marioStateMachine;

    public CapeMario(MarioStateMachine3 marioStateMachine){
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom() {
//        marioStateMachine.setCurrentState(State.SUPER);
    }

    @Override
    public void obtainCape() {

    }

    @Override
    public void obtainFireFlower() {
        marioStateMachine.setCurrentState(new FireMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        marioStateMachine.setCurrentState(new SuperMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() - 200);
    }
}
