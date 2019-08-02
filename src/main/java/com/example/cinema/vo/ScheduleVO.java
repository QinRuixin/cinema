package com.example.cinema.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 返回的按日期封装好的排片信息列表
 *
 * @author fjj
 * @date 2019/4/12 4:05 PM
 */
@Data
public class ScheduleVO {
    private Date date;
    private List<ScheduleItem> scheduleItemList;
}
