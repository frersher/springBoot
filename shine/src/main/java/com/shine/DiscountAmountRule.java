package com.shine;

import java.math.BigDecimal;

/**
 * @author chenbang
 * @description DiscountAmountRule
 * @date 2021/12/9 11:49 上午
 */
public interface DiscountAmountRule {
    /**
     * 优惠活动金额计算
     * @return
     */
    BigDecimal discountAmount(Integer type);
}
