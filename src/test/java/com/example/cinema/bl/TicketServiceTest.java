package com.example.cinema.bl;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.SeatForm;
import com.example.cinema.vo.TicketForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    TicketService ticketService;
    @Autowired
    TicketMapper ticketMapper;

    /**
     * 测试addTicket方法
     */
    @Test
    @Transactional
    public void addTicketTest(){
        SeatForm seatForm = new SeatForm();
        seatForm.setColumnIndex(1);
        seatForm.setRowIndex(1);
        List<SeatForm> seats = new ArrayList<>();
        seats.add(seatForm);

        seatForm.setColumnIndex(2);
        seatForm.setRowIndex(2);
        seats.add(seatForm);

        TicketForm ticketForm = new TicketForm();
        ticketForm.setSeats(seats);
        ticketForm.setUserId(-1);
        ticketForm.setScheduleId(67);
        ticketService.addTicket(ticketForm);
        ticketForm.setScheduleId(68);
        ticketService.addTicket(ticketForm);

        List<Ticket> ticket2 = ticketMapper.selectTicketByUser(-1);

//        Assert.assertEquals(ticket2.get(0).getScheduleId(),67);
//
//        Assert.assertEquals(ticket2.get(2).getScheduleId(),68);

    }
}
