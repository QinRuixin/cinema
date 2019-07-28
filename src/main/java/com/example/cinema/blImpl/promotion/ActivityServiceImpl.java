package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/20
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CouponService couponService;

    @Override
    @Transactional(rollbackFor = DuplicateKeyException.class)
    public ActivityVO publishActivity(ActivityForm activityForm) {
        Coupon coupon = couponService.addCoupon(activityForm.getCouponForm());
        Activity activity = new Activity();
        activity.setName(activityForm.getName());
        activity.setDescription(activityForm.getName());
        activity.setStartTime(activityForm.getStartTime());
        activity.setEndTime(activityForm.getEndTime());
        activity.setCouponId(coupon.getId());
        activityMapper.insertActivity(activity);
        if (activityForm.getMovieList() != null) {
            activityMapper.insertActivityAndMovie(activity.getId(), activityForm.getMovieList());
        }
        return activityMapper.selectById(activity.getId());
    }

    @Override
    public List<ActivityVO> getActivities() {
        return activityMapper.selectActivities();
    }

}
