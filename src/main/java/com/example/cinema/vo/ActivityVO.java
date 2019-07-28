package com.example.cinema.vo;

import com.example.cinema.po.Coupon;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author liying
 * @date 2019/4/20
 */
@Data
public class ActivityVO {
    private Integer id;
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
     * 优惠电影列表
     */
    private List<MovieVO> movieList;
    /**
     * 优惠券规格
     */
    private Coupon coupon;
}
