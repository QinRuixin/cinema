package com.example.cinema.vo;


import lombok.Data;

import java.util.List;

@Data
public class OrderForm {
    List<Integer> ticketId;
    int couponId;
}
