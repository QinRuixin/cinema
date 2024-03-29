package com.example.cinema.bl;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.vo.PlacingRateVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 测试MovieServiceImpl的方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTest {
    private Logger logger;

    @Autowired
    private StatisticsService statisticsService;

    @Before
    public void init(){
        logger = LoggerFactory.getLogger(this.getClass());
    }
    @Transactional
    @Test
    public void getMoviePlacingRateByDateTest(){
        Date date = new Date();
        List<PlacingRateVO> lists;
        String time = "2019-04-21";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        try {
            date1 = sdf.parse(time);
            lists = statisticsService.getMoviePlacingRateByDate(date1);
            for (int i = 0; i < lists.size(); i++) {
                logger.info(lists.get(i).getName());
                logger.info(lists.get(i).getRate().toString());
                logger.info("-------------------");
            }
//            Assert.assertEquals(lists.get(0).getName(),"夏目友人帐");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
