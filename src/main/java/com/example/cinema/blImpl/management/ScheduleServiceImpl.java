package com.example.cinema.blImpl.management;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.vo.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fjj
 * @date 2019/4/11 4:14 PM
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final String TIME_CONFLICT_ERROR_MESSAGE = "时间段冲突";
    private static final String CROSS_DAYS_ERROR_MESSAGE = "起止时间不能跨天";
    private static final String DATE_INTERVAL_LESS_THAN_LENGTH_ERROR_MESSAGE = "起止时间段不能少于电影时长或结束时间不能早于开始时间";
    private static final String BEFORE_NOW_TIME_ERROR_MESSAGE = "排片日期不能早于当前时间";
    private static final String BEFORE_START_DATE_ERROR_MESSAGE = "排片时间不能早于电影上映时间";
    private static final String MOVIE_NOT_EXIST_ERROR_MESSAGE = "电影不存在";
    private static final String HALL_NOT_EXIST_ERROR_MESSAGE = "影厅不存在";
    private static final String VIEW_COUNT_ERROR_MESSAGE = "排片可见限制数错误";
    private static final String ID_LIST_NULL_ERROR_MESSAGE = "id列表不可为空";
    private static final String VIEW_CONFLICT_ERROR_MESSAGE = "有排片信息已对观众可见，无法删除或修改";


    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private HallMapper hallMapper;

    @Override
    public @Nullable String addSchedule(ScheduleForm scheduleForm) {
        final String check = preCheck(scheduleForm);
        if (check != null) {
            return check;
        }
        scheduleMapper.insertOneSchedule(scheduleForm);
        return null;
    }

    @Override
    public @Nullable String updateSchedule(ScheduleForm scheduleForm) {
        final String check = preCheck(scheduleForm);
        if (check != null) {
            return check;
        }
        //在修改时要检查想要修改的排片信息是否已被观众可见，若可见则无法修改
        if (isAudienceCanView(Collections.singletonList(scheduleForm.getId()))) {
            return VIEW_CONFLICT_ERROR_MESSAGE;
        }
        scheduleMapper.updateScheduleById(scheduleForm);
        return null;
    }

    @Override
    public ScheduleItem getScheduleById(int id) {
        return scheduleMapper.selectScheduleById(id);
    }

    @Override
    public int getScheduleView() {
        return scheduleMapper.selectView();
    }

    @Override
    public List<ScheduleVO> searchAudienceSchedule(int movieId) {
        //根据view中设置的排片可见限制
        int days = scheduleMapper.selectView();
        List<ScheduleItem> scheduleItems = scheduleMapper.selectScheduleByMovieId(movieId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = getNumDayAfterDate(today, days);

        List<ScheduleItem> result = new ArrayList<>();
        for (ScheduleItem s : scheduleItems) {
            if (s.getStartTime().before(endDate) && s.getStartTime().after(new Date())) {
                result.add(s);
            }
        }
        int interval = 1;
        if (result.size() > 0) {
            interval = (int) ((result.get(result.size() - 1).getStartTime().getTime() - today.getTime()) / (1000 * 3600 * 24)) + 1;
        }
        return getScheduleVOList(interval, today, result);
    }

    @Override
    public List<ScheduleVO> searchScheduleSevenDays(int hallId, Date startDate) {
        // 处理查询表单的起止时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = simpleDateFormat.parse(simpleDateFormat.format(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = getNumDayAfterDate(startDate, 7);

        // 按照日期整理ScheduleItem
        List<ScheduleItem> scheduleItemList = scheduleMapper.selectSchedule(hallId, startDate, endDate);

        return getScheduleVOList(7, startDate, scheduleItemList);
    }

    @Override
    public @Nullable String setScheduleView(ScheduleViewForm scheduleViewForm) {
        if (scheduleViewForm.getDay() < 0) {
            return VIEW_COUNT_ERROR_MESSAGE;
        }

        int num = scheduleMapper.selectViewCount();
        if (num == 0) {
            scheduleMapper.insertOneView(scheduleViewForm);
        } else if (num == 1) {
            scheduleMapper.updateOneView(scheduleViewForm);
        } else {
            return VIEW_COUNT_ERROR_MESSAGE;
        }
        return null;
    }

    @Override
    public @Nullable String deleteBatchOfSchedule(ScheduleBatchDeleteForm scheduleBatchDeleteForm) {
        List<Integer> scheduleIdList = scheduleBatchDeleteForm.getScheduleIdList();
        if (scheduleIdList.size() == 0) {
            return ID_LIST_NULL_ERROR_MESSAGE;
        }

        if (isAudienceCanView(scheduleIdList)) {
            return VIEW_CONFLICT_ERROR_MESSAGE;
        }
        scheduleMapper.deleteScheduleBatch(scheduleIdList);
        return null;
    }


    /**
     * 获得num天后的日期
     *
     * @param oldDate
     * @param num
     * @return
     * @throws ParseException
     */
    private Date getNumDayAfterDate(Date oldDate, int num) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }

    /**
     * 新增或修改排片信息的公共前置检查
     *
     * @param scheduleForm
     * @return
     */
    private @Nullable String preCheck(ScheduleForm scheduleForm) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 检查排片时间是否早于当前时间
        if (scheduleForm.getStartTime().before(new Date())) {
            return BEFORE_NOW_TIME_ERROR_MESSAGE;
        }
        // 处理排片跨天错误
        if (!simpleDateFormat.format(scheduleForm.getStartTime()).equals(simpleDateFormat.format(scheduleForm.getEndTime()))) {
            return CROSS_DAYS_ERROR_MESSAGE;
        }

        //检查影厅是否存在
        if (hallMapper.selectHallById(scheduleForm.getHallId()) == null) {
            return HALL_NOT_EXIST_ERROR_MESSAGE;
        }

        // 检查电影是否存在
        MovieVO movieVO = movieMapper.selectMovieById(scheduleForm.getMovieId());
        if (movieVO == null) {
            return MOVIE_NOT_EXIST_ERROR_MESSAGE;
        }

        // 检查电影的上映时间是否和排片时间匹配
        if (scheduleForm.getStartTime().before(movieVO.getStartDate())) {
            return BEFORE_START_DATE_ERROR_MESSAGE;
        }

        // 校验排片时间段合法性
        int minutes = movieVO.getLength();
        Calendar calendarStartTime = Calendar.getInstance();
        calendarStartTime.setTime(scheduleForm.getStartTime());
        calendarStartTime.add(Calendar.MINUTE, minutes);
        Date endTime = calendarStartTime.getTime();
        if (scheduleForm.getEndTime().before(endTime)) {
            return DATE_INTERVAL_LESS_THAN_LENGTH_ERROR_MESSAGE;
        }

        // 检查该排片时间段是否和其他排片信息冲突
        if (0 != scheduleMapper.selectScheduleConflictByHallIdAndTime(scheduleForm.getHallId(), scheduleForm.getStartTime(), scheduleForm.getEndTime(), scheduleForm.getId()).size()) {
            return TIME_CONFLICT_ERROR_MESSAGE;
        }
        return null;
    }

    private boolean isAudienceCanView(List<Integer> scheduleIdList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = getNumDayAfterDate(today, scheduleMapper.selectView());

        List<ScheduleItem> scheduleList = scheduleMapper.selectScheduleBatch(scheduleIdList);
        for (ScheduleItem s : scheduleList) {
            if (s.getEndTime().before(endDate) && s.getEndTime().after(Objects.requireNonNull(today))) {
                return true;
            }
        }
        return false;
    }

    private List<ScheduleVO> getScheduleVOList(int interval, Date startDate, List<ScheduleItem> scheduleItemList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<ScheduleVO> scheduleVOList = new ArrayList<>();
        for (int i = 0; i < interval; i++) {
            Date date = getNumDayAfterDate(startDate, i);
            ScheduleVO scheduleVO = new ScheduleVO();
            scheduleVO.setDate(date);
            List<ScheduleItem> scheduleItems = new ArrayList<>();
            try {
                for (ScheduleItem scheduleItem : scheduleItemList) {
                    Date startTime = simpleDateFormat.parse(simpleDateFormat.format(scheduleItem.getStartTime()));
                    if (date.equals(startTime)) {
                        scheduleItems.add(scheduleItem);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            scheduleVO.setScheduleItemList(scheduleItems);
            scheduleVOList.add(scheduleVO);
        }
        return scheduleVOList;
    }
}
