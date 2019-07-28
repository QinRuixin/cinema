package com.example.cinema.vo;

import lombok.Data;

/**
 * @author liying
 * @date 2019/4/18
 */
@Data
public class SeatForm {
    /**
     * 列号
     */
    private int columnIndex;
    /**
     * 排号
     */
    private int rowIndex;
}
