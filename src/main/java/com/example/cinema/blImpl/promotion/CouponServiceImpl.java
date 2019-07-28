package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/17
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public List getCouponsByUser(int userId) {
        return couponMapper.selectCouponByUser(userId);
    }

    @Override
    public Coupon addCoupon(CouponForm couponForm) {
        Coupon coupon = new Coupon();
        coupon.setName(couponForm.getName());
        coupon.setDescription(couponForm.getDescription());
        coupon.setTargetAmount(couponForm.getTargetAmount());
        coupon.setDiscountAmount(couponForm.getDiscountAmount());
        coupon.setStartTime(couponForm.getStartTime());
        coupon.setEndTime(couponForm.getEndTime());
        couponMapper.insertCoupon(coupon);
        return coupon;
    }

    @Override
    public void issueCoupon(int couponId, int userId) {
        couponMapper.insertCouponUser(couponId, userId);
    }
}
