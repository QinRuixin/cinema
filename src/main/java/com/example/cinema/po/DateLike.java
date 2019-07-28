package com.example.cinema.po;


import lombok.Data;

import java.sql.Date;

/**
 * @author liying
 * @date 2019/3/23
 */
@Data
public class DateLike {
    /**
     * 喜爱人数
     */
    private int likeNum;
    /**
     * 喜爱时间
     */
    private Date likeTime;
}
