package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;
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
    public TicketWithCouponVO addTicket(TicketForm ticketForm) {
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
        //创建TicketWithCouponVO对象
        TicketWithCouponVO ticketWithCouponVO = new TicketWithCouponVO();

        //处理List<TicketVO>
        List<TicketVO> ticketVOs = new ArrayList<>();
        int size = tickets.size();
        for (int i = 0; i < size; i++) {
            TicketVO ticketVO = new TicketVO();
            Ticket ticket = tickets.get(i);
            ticketVO.setId(ticket.getId());
            ticketVO.setScheduleId(ticket.getScheduleId());
            ticketVO.setUserId(ticket.getUserId());
            ticketVO.setState(ticket.getState() == 0 ? "未完成" : "已完成");
            ticketVO.setTime(ticket.getTime());
            ticketVO.setRowIndex(ticket.getRowIndex());
            ticketVO.setColumnIndex(ticket.getColumnIndex());
            ticketVOs.add(ticketVO);
        }
        ticketWithCouponVO.setTicketVOList(ticketVOs);

        //处理List<ActivityVO>
        ScheduleItem scheduleItem = scheduleMapper.selectScheduleById(scheduleId);
        List<ActivityVO> activities = activityMapper.selectActivitiesByMovie(scheduleItem.getMovieId());
        ticketWithCouponVO.setActivities(activities);

        //处理List<Coupon>
        double amount = tickets.size() * scheduleItem.getFare();
        double maxDiscount = 0;
        List<Coupon> coupons = couponMapper.selectCouponByUserAndAmount(
                userId, amount);
        ticketWithCouponVO.setCoupons(coupons);

        //处理total
        if (coupons != null) {
            int num = coupons.size();
            for (int i = 0; i < num; i++) {
                double temp = coupons.get(i).getDiscountAmount();
                if(temp>maxDiscount){
                    maxDiscount = temp;
                }
            }
        }
        ticketWithCouponVO.setTotal(amount - maxDiscount);

        return ticketWithCouponVO;
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
