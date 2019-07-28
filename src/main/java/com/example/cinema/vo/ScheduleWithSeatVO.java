package com.example.cinema.vo;

import lombok.Data;

/**
 * @author liying
 * @date 2019/4/21
 */
@Data
public class ScheduleWithSeatVO {
    /**
     * 排片
     */
    private ScheduleItem scheduleItem;
    /**
     * 座位
     */
    private int[][] seats;
}
