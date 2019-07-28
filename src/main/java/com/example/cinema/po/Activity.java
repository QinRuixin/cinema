package com.example.cinema.po;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liying
 * @date 2019/4/20
 */
@Data
public class Activity {
    private int id;
    /**
     * 优惠活动名称
     */
    private String name;
    /**
     * 优惠活动描述
     */
    private String description;
    /**
     * 优惠活动开始时间
     */
    private Timestamp startTime;
    /**
     * 优惠活动截止时间
     */
    private Timestamp endTime;
    /**
     * 优惠券id
     */
    private int couponId;
}
