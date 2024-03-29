package com.example.cinema.bl.management;

import com.example.cinema.vo.HallVO;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     *
     * @return
     */
    List<HallVO> searchAllHall();
}
