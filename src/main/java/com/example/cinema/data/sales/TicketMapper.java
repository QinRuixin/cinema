package com.example.cinema.data.sales;

import com.example.cinema.po.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author liying
 * @date 2019/4/16
 */
@Mapper
public interface TicketMapper {
    /**
     * @param ticket
     * @return
     */
    int insertTicket(Ticket ticket);

    /**
     * @param tickets
     * @return
     */
    int insertTickets(List<Ticket> tickets);

    /**
     * @param ticketId
     */
    void deleteTicket(int ticketId);

    /**
     * @param ticketId
     * @param state
     */
    void updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    /**
     * @param scheduleId
     * @return
     */
    List<Ticket> selectTicketsBySchedule(int scheduleId);

    /**
     * @param scheduleId
     * @param columnIndex
     * @param rowIndex
     * @return
     */
    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    /**
     * @param id
     * @return
     */
    Ticket selectTicketById(int id);

    /**
     * @param userId
     * @return
     */
    List<Ticket> selectTicketByUser(int userId);

    /**
     * 定时修改超时票
     */
    //xml文件中TIMESTAMPDIFF(1,2,3) 结果为参数3-参数2
    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredTicket();
}

