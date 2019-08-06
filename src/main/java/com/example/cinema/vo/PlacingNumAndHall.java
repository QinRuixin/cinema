package com.example.cinema.vo;

import lombok.Data;

/**
 * 某天某影厅电影购票人数及影厅大小
 *
 * @author qin
 * @date 2019/8/3 5:14 PM
 */
@Data
public class PlacingNumAndHall {
    private String movieName;
    private Integer num;
    private Integer movieId;
    private Integer hallId;
    private Integer row;
    private Integer column;
}
