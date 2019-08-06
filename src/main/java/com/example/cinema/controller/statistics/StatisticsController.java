package com.example.cinema.controller.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /**
     * @param date 非必填参数，若不填则默认为当天
     */
    @GetMapping("statistics/scheduleRate")
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date) {
        try {
            return ResponseVO.buildSuccess(statisticsService.getScheduleRateByDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.toString());
        }
    }

    @GetMapping("statistics/boxOffice/total")
    public ResponseVO getTotalBoxOffice() {
        return ResponseVO.buildSuccess(statisticsService.getTotalBoxOffice());
    }

    @GetMapping("statistics/audience/price")
    public ResponseVO getAudiencePrice() {
        try {
            return ResponseVO.buildSuccess(statisticsService.getAudiencePriceSevenDays());
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.toString());
        }
    }

    @GetMapping("statistics/placingRate")
    public ResponseVO getMoviePlacingRateByDate(@RequestParam Date date) {
        try {
            return ResponseVO.buildSuccess(statisticsService.getMoviePlacingRateByDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.toString());
        }
    }

    @GetMapping("statistics/popular/movie")
    public ResponseVO getPopularMovies(@RequestParam int days, @RequestParam int movieNum) {
        try {
            return ResponseVO.buildSuccess(statisticsService.getPopularMovies(days, movieNum));
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.toString());
        }
    }


}
