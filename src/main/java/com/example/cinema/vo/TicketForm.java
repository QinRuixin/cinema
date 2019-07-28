package com.example.cinema.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/16
 */
@Data
public class TicketForm {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 排片id
     */
    private int scheduleId;
    private List<SeatForm> seats;
}
