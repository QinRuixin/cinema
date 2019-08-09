package com.example.cinema.interceptor;

import com.example.cinema.data.sales.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanScheduler {
    @Autowired
    TicketMapper ticketMapper;
//
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void cleanExpiredTicket(){
//        ticketMapper.cleanExpiredTicket();
//        System.out.println("clean");
//    }
}
