package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public List getScheduleRateByDate(Date date) throws ParseException {
        Date requireDate = date;
        if (requireDate == null) {
            requireDate = new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));
        Date nextDate = getNumDayAfterDate(requireDate, 1);
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate);
        return movieScheduleTimeVOList;
    }

    @Override
    public List getTotalBoxOffice() {
        return statisticsMapper.selectMovieTotalBoxOffice();
    }

    @Override
    public List getAudiencePriceSevenDays() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
//        today = getNumDayAfterDate(today,1);
        Date startDate = getNumDayAfterDate(today, -6);
        List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
            Date date = getNumDayAfterDate(startDate, i);
            audiencePriceVO.setDate(date);
            List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));

            double totalPrice = audiencePriceList.stream().mapToDouble(AudiencePrice::getTotalPrice).sum();
            audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
            audiencePriceVOList.add(audiencePriceVO);
        }
        return audiencePriceVOList;
    }

    @Override
    public List getMoviePlacingRateByDate(Date date) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = simpleDateFormat.parse(simpleDateFormat.format(date));
        Date nextDate = getNumDayAfterDate(date,1);
        List<PlacingNumAndHall> numAndHalls = statisticsMapper.selectPlacingNumAndHall(date,nextDate);
        List<PlacingRateVO> moviePlacingRateVOList = new ArrayList<>();
        HashMap<Integer,Integer> idAndNum = new HashMap<>();

        for (PlacingNumAndHall numAndHall : numAndHalls) {
            int movieId = numAndHall.getMovieId();
            if (!idAndNum.containsKey(movieId)) {
                idAndNum.put(movieId, 1);
            } else {
                idAndNum.put(movieId, 2);
            }
        }

        for (int i = 0; i < numAndHalls.size(); i++) {
            PlacingRateVO temp = new PlacingRateVO();
            PlacingNumAndHall now = numAndHalls.get(i);
            int movieId = now.getMovieId();
            temp.setMovieId(movieId);
            temp.setName(now.getMovieName());

            if(idAndNum.get(movieId)==1){
//                double rate = (double)now.getNum()/(now.getRow()*now.getColumn());
                temp.setRate(Double.parseDouble(String.format("%.2f",
                        now.getNum()/(double)(now.getRow()*now.getColumn()))));

            }else {
                int num1 = now.getNum();
                int total1 = now.getRow()*now.getColumn();
                i++;
                now = numAndHalls.get(i);
                int num2 = now.getNum();
                int total2 = now.getRow()*now.getColumn();
                temp.setRate(Double.parseDouble(String.format("%.2f",
                        (num1+num2)/(double)(total1+total2))));
            }
            moviePlacingRateVOList.add(temp);
        }
        return moviePlacingRateVOList;
    }

    @Override
    public List getPopularMovies(int days, int movieNum) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        endDate = getNumDayAfterDate(endDate,1);
        Date startDate = getNumDayAfterDate(endDate,-days);
        return statisticsMapper.selectMovieBoxOfficeSeveralDays(startDate,endDate,movieNum);
    }


    /**
     * 获得num天后的日期
     */
    private Date getNumDayAfterDate(Date oldDate, int num) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }
}
