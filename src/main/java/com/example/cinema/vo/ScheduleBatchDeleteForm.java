package com.example.cinema.vo;

import lombok.Data;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/13 3:57 PM
 */
@Data
public class ScheduleBatchDeleteForm {
    /**
     * 要删除的排片信息id列表
     */
    private List<Integer> scheduleIdList;
}
