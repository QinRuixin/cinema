package com.example.cinema.vo;

import lombok.Data;

import java.util.Date;

/**
 * 返回的排片信息表
 * @author fjj
 * @date 2019/4/12 3:34 PM
 */
@Data
public class ScheduleItem {
    /**
     * id
     */
    private Integer id;
    /**
     * 影厅id
     */
    private Integer hallId;
    /**
     * 影厅名称
     */
    private String hallName;
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 电影名
     */
    private String movieName;
    /**
     * 开始放映时间
     */
    private Date startTime;
    /**
     * 结束放映时间
     */
    private Date endTime;
    /**
     * 票价
     */
    private double fare;
}
