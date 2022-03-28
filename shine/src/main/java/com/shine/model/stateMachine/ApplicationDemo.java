package com.shine.model.stateMachine;

import com.shine.model.stateMachine.model.MarioStateMachine3;

/**
 * @program: demo
 * @description:
 * @author: bang.chen
 * @create: 2022-03-28 20:26
 **/
public class ApplicationDemo {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainFireFlower();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);


        MarioStateMachine1 mario1 = new MarioStateMachine1();
        mario1.obtainFireFlower();
        int score1 = mario.getScore();
        State state1 = mario.getCurrentState();
        System.out.println("mario score: " + score1 + "; state: " + state1);


        MarioStateMachine3 mario3 = new MarioStateMachine3();
        mario3.obtainFireFlower();
        int score3 = mario.getScore();
        State state3 = mario.getCurrentState();
        System.out.println("mario score: " + score3 + "; state: " + state3);
    }
}
