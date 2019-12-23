package com.wudimanong.oauth.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangqiao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthUser implements Serializable {

    /**
     * 用户唯一编码
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户登录密码
     */
    private String password;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 性别 male 男、female 女
     */
    private String gender;
    /**
     * 年龄
     */
    private int age;
    /**
     * 是否活跃 0 禁用、1 启用
     */
    private int active;
    private Timestamp createDate;
    private Timestamp updateDate;
}
