package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.vo.OrderForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author liying
 * @date 2019/4/16
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody OrderForm orderForm) {
        ticketService.completeByVIPCard(orderForm.getTicketId(),orderForm.getCouponId());
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody OrderForm orderForm) {
        ticketService.completeByVIPCard(orderForm.getTicketId(),orderForm.getCouponId());
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm) {
        return ResponseVO.buildSuccess(ticketService.addTicket(ticketForm));
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId) {
        return ResponseVO.buildSuccess( ticketService.getTicketByUser(userId));
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId) {
        return ResponseVO.buildSuccess(ticketService.getBySchedule(scheduleId));
    }

    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseVO.buildSuccess();
    }

}
