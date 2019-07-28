package com.example.cinema.bl.sales;

import com.example.cinema.vo.ScheduleWithSeatVO;
import com.example.cinema.vo.TicketForm;

import java.util.List;


/**
 * @author liying
 * @date 2019/4/16
 */
public interface TicketService {
    /**
     * TODO:锁座【增加票但状态为未付款】
     *
     * @param ticketForm
     */
    void addTicket(TicketForm ticketForm);

    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券
     *
     * @param id
     * @param couponId
     */
    void completeTicket(List<Integer> id, int couponId);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ScheduleWithSeatVO getBySchedule(int scheduleId);

    /**
     * TODO:获得用户买过的票
     *
     * @param userId
     */
    void getTicketByUser(int userId);

    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param id
     * @param couponId
     */
    void completeByVIPCard(List<Integer> id, int couponId);

    /**
     * TODO:取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param id
     */
    void cancelTicket(List<Integer> id);
}
