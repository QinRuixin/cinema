package com.example.cinema.bl;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    private Logger logger;
    @Autowired
    private AccountService accountService;

    /**
     * 测试AccountServiceImpl的registerAccount和非空的login方法
     */
    @Test
    @Transactional
    public void registerAccountAndLoginTest(){
        UserForm userForm = new UserForm();
        userForm.setUsername("JUniteTest");
        userForm.setPassword("123456");
        accountService.registerAccount(userForm);
        logger = LoggerFactory.getLogger(getClass());

        UserVO actual = accountService.login(userForm);
//        logger.info(actual.getId().toString());
        Assert.assertEquals("JUniteTest",actual.getUsername());

    }

    /**
     * 测试AccountServiceImpl的参数为空的login方法
     */
    @Test
    @Transactional
    public void loginTest() {
        UserForm userForm = new UserForm();
        userForm.setUsername("JUniteTest");
        userForm.setPassword("123456");

        logger = LoggerFactory.getLogger(getClass());

        UserVO actual = accountService.login(userForm);
//        logger.info(actual.getId().toString());
        Assert.assertNull(actual);
    }

}
