package com.example.cinema.data.statistics;

import com.example.cinema.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询date日期每部电影的排片次数
     *
     * @param date
     * @return
     */
    List<MovieScheduleTimeVO> selectMovieScheduleTimes(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的总票房（包括已经下架的，降序排列）
     *
     * @return
     */
    List<MovieTotalBoxOfficeVO> selectMovieTotalBoxOffice();

    /**
     * 查询某天每个客户的购票金额
     *
     * @param date
     * @param nextDate
     * @return
     */
    List<AudiencePrice> selectAudiencePrice(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询某天某影厅电影购票人数及影厅大小
     *TODO 存在bug，当其中一个影厅无人购票时不会被计算进去
     *
     * @param date
     * @param nextDate
     * @return
     */
    List<PlacingNumAndHall> selectPlacingNumAndHall(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询某段时间内票房排行前movieNum的电影及票房
     *
     * @param startDate
     * @param endDate
     * @param movieNum
     * @return
     */
    List<MovieBoxOfficeSeveralDaysVO> selectMovieBoxOfficeSeveralDays(Date startDate,Date endDate,int movieNum);

}
