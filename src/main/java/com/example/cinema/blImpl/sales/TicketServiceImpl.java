package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    HallMapper hallmapper;
    @Autowired
    ActivityMapper activityMapper;

    @Override
    @Transactional
    public void addTicket(TicketForm ticketForm) {
        List<SeatForm> seats = ticketForm.getSeats();
        int userId = ticketForm.getUserId();
        int scheduleId = ticketForm.getScheduleId();
        List<Ticket> tickets = new ArrayList<>();

        seats.forEach(seatForm -> {
            Ticket ticket = new Ticket();
            ticket.setUserId(userId);
            ticket.setScheduleId(scheduleId);
            ticket.setState(0);
            ticket.setColumnIndex(seatForm.getColumnIndex());
            ticket.setRowIndex(seatForm.getRowIndex());
            tickets.add(ticket);
        });
        ticketMapper.insertTickets(tickets);
    }

    @Override
    @Transactional
    public void completeTicket(List<Integer> id, int couponId) {
    }

    @Override
    public ScheduleWithSeatVO getBySchedule(int scheduleId) {
        List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
        ScheduleItem schedule = scheduleMapper.selectScheduleById(scheduleId);
        HallVO hall = hallmapper.selectHallById(schedule.getHallId());
        int[][] seats = new int[hall.getRow()][hall.getColumn()];
        tickets.forEach(ticket -> {
            seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;
        });
        ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
        scheduleWithSeatVO.setScheduleItem(schedule);
        scheduleWithSeatVO.setSeats(seats);
        return scheduleWithSeatVO;
    }

    @Override
    public void getTicketByUser(int userId) {
    }

    @Override
    @Transactional
    public void completeByVIPCard(List<Integer> id, int couponId) {
    }

    @Override
    public void cancelTicket(List<Integer> id) {
    }

}
