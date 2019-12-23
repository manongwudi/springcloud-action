package com.wudimanong.oauth.resource.controller;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthUser;
import com.wudimanong.oauth.resource.service.OauthUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@Slf4j
@RestController
@RequestMapping(value = "/internel")
public class OauthUserController {

    @Autowired
    OauthUserService oauthUserServiceImpl;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "/user/name/{userName}")
    public ResponseData<OauthUser> getUserByUserName(@PathVariable("userName") String userName) {
        log.debug("根据用户名查询用户");
        if (StringUtils.isEmpty(userName)) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        OauthUser baseUser = oauthUserServiceImpl.selectOneByUserName(userName);
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), baseUser);
    }
}
