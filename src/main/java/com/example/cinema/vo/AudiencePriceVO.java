package com.example.cinema.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author fjj
 * @date 2019/4/21 3:00 PM
 */
@Data
public class AudiencePriceVO {
    private Date date;
    /**
     * 客单价
     */
    private Double price;
}
