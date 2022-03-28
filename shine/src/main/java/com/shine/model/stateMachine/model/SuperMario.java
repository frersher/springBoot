package com.shine.model.stateMachine.model;

import com.shine.model.stateMachine.State;

/**
 * @program: demo
 * @description:
 * @author: bang.chen
 * @create: 2022-03-28 21:12
 **/
public class SuperMario implements IMario{

    private MarioStateMachine3 marioStateMachine;

    public SuperMario(MarioStateMachine3 marioStateMachine){
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {

    }

    @Override
    public void obtainCape() {
        marioStateMachine.setCurrentState(new CapeMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        marioStateMachine.setCurrentState(new FireMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        marioStateMachine.setCurrentState(new SmallMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() - 100);
    }
}
