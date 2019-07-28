package com.example.cinema.blImpl.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.vo.HallVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallMapper hallMapper;

    @Override
    public List<HallVO> searchAllHall() {
        return hallMapper.selectAllHall();
    }
}
