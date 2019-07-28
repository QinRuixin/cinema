package com.example.cinema.vo;

import com.example.cinema.po.Coupon;
import lombok.Data;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/21
 */
@Data
public class TicketWithCouponVO {
    private List<TicketVO> ticketVOList;
    private double total;
    private List<Coupon> coupons;
    private List<ActivityVO> activities;
}
