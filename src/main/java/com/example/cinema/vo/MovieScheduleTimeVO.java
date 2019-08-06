package com.example.cinema.vo;

import lombok.Data;

/**
 * 查询返回的排片次数表
 *
 * @author fjj
 * @date 2019/4/16 2:48 PM
 */
@Data
public class MovieScheduleTimeVO {
    private Integer movieId;
    /**
     * 排片次数
     */
    private Integer time;
    private String name;
}
