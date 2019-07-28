package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseVO buyTicketByVIPCard(@RequestParam List<Integer> ticketId, @RequestParam int couponId) {
        ticketService.completeByVIPCard(ticketId, couponId);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm) {
        ticketService.addTicket(ticketForm);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestParam List<Integer> ticketId, @RequestParam int couponId) {
        ticketService.completeTicket(ticketId, couponId);
        return ResponseVO.buildSuccess();
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId) {
        ticketService.getTicketByUser(userId);
        return ResponseVO.buildSuccess();
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
