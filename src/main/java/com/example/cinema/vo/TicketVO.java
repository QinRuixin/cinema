package com.example.cinema.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liying
 * @date 2019/4/16
 */
@Data
public class TicketVO {
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
     * 订单状态
     */
    private String state;
    private Timestamp time;
}
