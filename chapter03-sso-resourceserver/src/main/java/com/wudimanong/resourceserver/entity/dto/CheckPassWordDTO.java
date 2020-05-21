package com.wudimanong.resourceserver.entity.dto;

import lombok.Data;

/**
 * @author jiangqiao
 */
@Data
public class CheckPassWordDTO {

    /**
     * 登录账号
     */
    private String userName;
    /**
     * 登录密码
     */
    private String passWord;

}
