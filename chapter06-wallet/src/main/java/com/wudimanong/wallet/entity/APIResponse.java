package com.wudimanong.wallet.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @author joe
 */
@Data
@ToString
public class APIResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public static <T> APIResponse<T> success(int code, String message, T data) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> APIResponse<T> error(int code, String message) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
