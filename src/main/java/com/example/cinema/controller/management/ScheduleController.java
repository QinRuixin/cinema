package com.example.cinema.controller.management;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.ScheduleBatchDeleteForm;
import com.example.cinema.vo.ScheduleForm;
import com.example.cinema.vo.ScheduleViewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 排片管理
 *
 * @author fjj
 * @date 2019/4/11 4:13 PM
 */
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    public ResponseVO addSchedule(@RequestBody ScheduleForm scheduleForm) {
        final String s = scheduleService.addSchedule(scheduleForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        } else {
            return ResponseVO.buildSuccess();
        }
    }

    @RequestMapping(value = "/schedule/update", method = RequestMethod.POST)
    public ResponseVO updateSchedule(@RequestBody ScheduleForm scheduleForm) {
        final String s = scheduleService.updateSchedule(scheduleForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        } else {
            return ResponseVO.buildSuccess();
        }
    }

    @RequestMapping(value = "/schedule/search", method = RequestMethod.GET)
    public ResponseVO searchSchedule(@RequestParam int hallId, @RequestParam Date startDate) {
        //这里传递startDate参数时，前端传的是用/分隔的时间，例如startDate=2019/04/12
        return ResponseVO.buildSuccess(scheduleService.searchScheduleSevenDays(hallId, startDate));
    }

    @RequestMapping(value = "/schedule/search/audience", method = RequestMethod.GET)
    public ResponseVO searchAudienceSchedule(@RequestParam int movieId) {
        return ResponseVO.buildSuccess(scheduleService.searchAudienceSchedule(movieId));
    }

    @RequestMapping(value = "/schedule/view/set", method = RequestMethod.POST)
    public ResponseVO setScheduleView(@RequestBody ScheduleViewForm scheduleViewForm) {
        final String s = scheduleService.setScheduleView(scheduleViewForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        } else {
            return ResponseVO.buildSuccess();
        }
    }

    @RequestMapping(value = "/schedule/view", method = RequestMethod.GET)
    public ResponseVO getScheduleView() {
        return ResponseVO.buildSuccess(scheduleService.getScheduleView());
    }


    @RequestMapping(value = "/schedule/delete/batch", method = RequestMethod.DELETE)
    public ResponseVO deleteBatchOfSchedule(@RequestBody ScheduleBatchDeleteForm scheduleBatchDeleteForm) {
        final String s = scheduleService.deleteBatchOfSchedule(scheduleBatchDeleteForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        } else {
            return ResponseVO.buildSuccess();
        }
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    public ResponseVO getScheduleById(@PathVariable int id) {
        return ResponseVO.buildSuccess(scheduleService.getScheduleById(id));
    }


}
