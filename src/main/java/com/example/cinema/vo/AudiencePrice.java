package com.example.cinema.vo;

import lombok.Data;

/**
 * （某天）一个客户的购票总金额
 *
 * @author fjj
 * @date 2019/4/21 2:51 PM
 */
@Data
public class AudiencePrice {
    private Integer userId;
    private Double totalPrice;
}
