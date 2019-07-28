package com.example.cinema.vo;

import lombok.Data;

/**
 * @author fjj
 * @date 2019/4/21 1:42 PM
 */
@Data
public class MovieTotalBoxOfficeVO {
    private Integer movieId;
    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private Integer boxOffice;
    private String name;
}
