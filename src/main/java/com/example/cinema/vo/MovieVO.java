package com.example.cinema.vo;

import lombok.Data;

import java.util.Date;

/**
 * 返回的电影信息表
 * @author fjj
 * @date 2019/3/23 1:12 PM
 */
@Data
public class MovieVO {
    /**
     * 电影id
     */
    private Integer id;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 海报url
     */
    private String posterUrl;
    /**
     * 导演
     */
    private String director;
    /**
     * 编剧
     */
    private String screenWriter;
    /**
     * 主演
     */
    private String starring;
    /**
     * 电影类型
     */
    private String type;
    /**
     * 制片国家/地区
     */
    private String country;
    /**
     * 语言
     */
    private String language;
    /**
     * 上映时间
     */
    private Date startDate;

    /**
     * 片长
     */
    private Integer length;
    /**
     * 描述
     *
     * @return
     */
    private String description;
    /**
     * 电影状态，0：上架状态，1：下架状态
     */
    private Integer status;
    /**
     * 是否想看,0:未标记想看，1：已标记想看
     */
    private Integer islike;
    /**
     * 想看人数
     *
     * @return
     */
    private Integer likeCount;
}
