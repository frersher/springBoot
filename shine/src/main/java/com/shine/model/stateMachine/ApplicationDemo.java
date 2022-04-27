package com.shine.model.stateMachine;

import com.shine.model.stateMachine.model.MarioStateMachine3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: demo
 * @description:
 * @author: bang.chen
 * @create: 2022-03-28 20:26
 **/
public class ApplicationDemo {
    public static void main(String[] args) {
//        MarioStateMachine mario = new MarioStateMachine();
//        mario.obtainFireFlower();
//        int score = mario.getScore();
//        State state = mario.getCurrentState();
//        System.out.println("mario score: " + score + "; state: " + state);
//
//
//        MarioStateMachine1 mario1 = new MarioStateMachine1();
//        mario1.obtainFireFlower();
//        int score1 = mario.getScore();
//        State state1 = mario.getCurrentState();
//        System.out.println("mario score: " + score1 + "; state: " + state1);
//
//
//        MarioStateMachine3 mario3 = new MarioStateMachine3();
//        mario3.obtainFireFlower();
//        int score3 = mario.getScore();
//        State state3 = mario.getCurrentState();
//        System.out.println("mario score: " + score3 + "; state: " + state3);

        String str = "111222";
        String replace = str.replace("111", "");
        System.out.println(replace);

        System.out.println(isValidNew("()"));
        System.out.println(isValidNew("([)]"));
    }


//
	public static boolean isValid(String s) {
        String replace = s.replace("()", "").replace("[]", "").replace("{}", "");
        if (s.length() == replace.length()){
            return false;
        }
       while (replace.length() > 0){
           isValid(replace);
       }
        return true;
    }

    public static boolean isValidNew(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> charMap = new HashMap<>();
        charMap.put('(', ')');
        charMap.put('[', ']');
        charMap.put('{', '}');
        for (char c:s.toCharArray()){
            if (null ==  charMap.get(c)){
                if ((stack.isEmpty() || stack.pop() != c)) {
                    return false;
                }
            }else {
                stack.push(charMap.get(c));
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }





}
