package com.example.cinema.bl.statistics;

import com.example.cinema.vo.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
public interface StatisticsService {
    /**
     * 获取某日各影片排片率统计数据
     *
     * @param date
     * @return
     */
    List<MovieScheduleTimeVO> getScheduleRateByDate(Date date) throws ParseException;

    /**
     * 获取所有电影的累计票房(降序排序，且包含已下架的电影)
     *
     * @return
     */
    List<MovieTotalBoxOfficeVO> getTotalBoxOffice();

    /**
     * 客单价：（某天的客单价=当天观众购票所花金额/购票人次数）
     * 返回值为过去7天内每天客单价
     *
     * @return
     */
    List<AudiencePriceVO> getAudiencePriceSevenDays() throws ParseException;


    /**
     * 获取所有电影某天的上座率
     * 上座率参考公式：假设某影城设有n 个电影厅、m 个座位数，相对上座率=观众人次÷放映场次÷m÷n×100%
     * 公式有误？
     * 当日某影片上座率=当日该影片观影人数/∑【当日每场电影放映场次*每场所在影厅的座位数】
     *
     * @param date
     * @return
     */
    List<PlacingRateVO> getMoviePlacingRateByDate(Date date) throws ParseException;

    /**
     * 获取最近days天内，最受欢迎的movieNum个电影(可以简单理解为最近days内票房越高的电影越受欢迎)
     *
     * @param days
     * @param movieNum
     * @return
     */
    List<MovieBoxOfficeSeveralDaysVO> getPopularMovies(int days, int movieNum) throws ParseException;

}
