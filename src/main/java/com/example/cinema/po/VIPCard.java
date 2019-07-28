package com.example.cinema.po;


import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liying
 * @date 2019/4/14
 */
@Data
public class VIPCard {
    public static final double PRICE = 25;
    public static final String DESCRIPTION = "满200送30";

    /**
     * 用户id
     */
    private int userId;
    /**
     * 会员卡id
     */
    private int id;
    /**
     * 会员卡余额
     */
    private double balance;
    /**
     * 办卡日期
     */
    private Timestamp joinDate;

    public double calculate(double amount) {
        return (int) (amount / 200) * 30 + amount;
    }
}
