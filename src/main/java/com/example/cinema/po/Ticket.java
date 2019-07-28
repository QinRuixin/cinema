package com.example.cinema.po;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liying
 * @date 2019/4/16
 */
@Data
public class Ticket {
    /**
     * 电影票id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 排片id
     */
    private int scheduleId;
    /**
     * 列号
     */
    private int columnIndex;
    /**
     * 排号
     */
    private int rowIndex;
    /**
     * 订单状态：
     * 0：未完成 1：已完成 2:已失效
     */
    private int state;
    private Timestamp time;
}
