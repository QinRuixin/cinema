package com.example.cinema.vo;

import lombok.Data;

/**
 * @author liying
 * @date 2019/4/14
 */
@Data
public class VIPCardForm {
    /**
     * vip卡id
     */
    private int vipId;
    /**
     * 付款金额
     */
    private int amount;
}
