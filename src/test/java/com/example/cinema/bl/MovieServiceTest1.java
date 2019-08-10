package com.example.cinema.bl;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.vo.MovieForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.mockito.Mockito.mock;

/**
 * 测试MovieServiceImpl的方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest1 {
    private Logger logger;

    @Autowired
    private MovieService movieService;

    private MovieForm movieForm;

    /**
     * 准备测试用例
     */
    @Before
    public void setUp(){
//        MovieForm mock = mock(MovieForm.class);
        movieForm = new MovieForm();
        movieForm.setCountry("China");
        movieForm.setDescription("hello");
        movieForm.setDirector("I");
        movieForm.setLanguage("Chinese");
        movieForm.setLength(120);
        movieForm.setName("JunitTest1");
        movieForm.setPosterUrl("http://b-ssl.duitang.com/uploads/item/201606/18/20160618164702_E4vcS.jpeg");
        movieForm.setScreenWriter("I");
        movieForm.setStarring("Tom");
        movieForm.setType("Testing");
        movieForm.setStartDate(new Date());
        movieForm.setStatus(0);
        logger = LoggerFactory.getLogger(getClass());

    }


    /**
     * 测试addMovie添加成功
     */
    @Test
    @Transactional
    public void addMovieTest1(){
        movieService.addMovie(movieForm);
        int temp1 = movieForm.getId();
//        logger.info(movieForm.getId().toString());

        movieForm.setName("JunitTest2");
        movieService.addMovie(movieForm);
        int temp2 = movieForm.getId();

        temp1++;
        Assert.assertEquals(temp1,temp2);
//        logger.info(movieForm.getId().toString());
    }

}
