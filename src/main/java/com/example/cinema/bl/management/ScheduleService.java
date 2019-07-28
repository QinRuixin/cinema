package com.example.cinema.bl.management;

import com.example.cinema.vo.*;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 4:14 PM
 */
public interface ScheduleService {
    /**
     * 添加排片信息
     *
     * @param scheduleForm
     * @return
     */
    @Nullable String addSchedule(ScheduleForm scheduleForm);

    /**
     * 查询包括从起始时间开始的7天排片计划
     *
     * @param hallId
     * @param startDate
     * @return
     */
    List<ScheduleVO> searchScheduleSevenDays(int hallId, Date startDate);

    /**
     * 设置排片对观众的可见的天数(全局设置,暂时只涉及天数)
     * 若设置7天，且今天是04-11，则观众可见04-11到04-17的排片信息，其他均不可见
     *
     * @param scheduleViewForm
     * @return
     */
    @Nullable String setScheduleView(ScheduleViewForm scheduleViewForm);

    /**
     * 批量删除排片信息
     *
     * @param scheduleBatchDeleteForm
     * @return
     */
    @Nullable String deleteBatchOfSchedule(ScheduleBatchDeleteForm scheduleBatchDeleteForm);

    /**
     * 修改排片信息
     *
     * @param scheduleForm
     * @return
     */
    @Nullable String updateSchedule(ScheduleForm scheduleForm);

    /**
     * 根据id获取schedule
     *
     * @param id
     * @return
     */
    ScheduleItem getScheduleById(int id);

    /**
     * 查询排片对观众的可见的天数
     *
     * @return
     */
    int getScheduleView();

    /**
     * 观众看到的排片信息
     *
     * @param movieId
     * @return
     */
    List<ScheduleVO> searchAudienceSchedule(int movieId);
}
