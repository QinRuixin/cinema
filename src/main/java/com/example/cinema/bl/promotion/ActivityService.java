package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ActivityVO;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/20
 */
public interface ActivityService {

    ActivityVO publishActivity(ActivityForm activityForm);

    List<ActivityVO> getActivities();

}
