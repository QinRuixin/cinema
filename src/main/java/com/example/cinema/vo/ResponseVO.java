package com.example.cinema.vo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author fjj
 * @date 2019/3/12 5:14 PM
 */
@Data
public class ResponseVO {
    /**
     * 调用是否成功
     */
    private Boolean success;
    /**
     * 返回的提示信息
     */
    private String message;
    /**
     * 内容
     */
    private Object content;

    public static ResponseVO buildSuccess() {
        ResponseVO response = new ResponseVO();
        response.setSuccess(true);
        return response;
    }

    public static ResponseVO buildSuccess(@NotNull Object content) {
        ResponseVO response = new ResponseVO();
        response.setContent(content);
        response.setSuccess(true);
        return response;
    }

    public static ResponseVO buildFailure(@NotNull String message) {
        ResponseVO response = new ResponseVO();
        response.setSuccess(false);
        response.setMessage(message);
        System.out.println(message);
        return response;
    }
}
