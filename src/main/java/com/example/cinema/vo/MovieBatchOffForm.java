package com.example.cinema.vo;

import lombok.Data;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 9:51 PM
 */
@Data
public class MovieBatchOffForm {
    /**
     * 要下架的电影id列表
     */
    private List<Integer> movieIdList;
}
