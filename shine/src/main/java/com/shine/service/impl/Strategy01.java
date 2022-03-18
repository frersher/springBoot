package com.shine.service.impl;

import com.shine.service.StrategyMain;
import org.springframework.stereotype.Component;

/**
 * @author chenbang
 * @description Strategy01
 * @date 2021/8/23 5:29 下午
 */
@Component
public class Strategy01 extends StrategyMain {
    @Override
    public void doAction() {
        System.out.println("Strategy01");
    }
}
