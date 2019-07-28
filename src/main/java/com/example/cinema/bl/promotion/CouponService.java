package com.example.cinema.bl.promotion;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;

import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
public interface CouponService {

    List getCouponsByUser(int userId);

    Coupon addCoupon(CouponForm couponForm);

    void issueCoupon(int couponId, int userId);

}
