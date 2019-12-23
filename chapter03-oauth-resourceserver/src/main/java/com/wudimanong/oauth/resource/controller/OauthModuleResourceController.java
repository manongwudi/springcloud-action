package com.wudimanong.oauth.resource.controller;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.resource.service.OauthModuleResourceService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@Slf4j
@RestController
@RequestMapping(value = "/internel")
public class OauthModuleResourceController {

    @Autowired
    OauthModuleResourceService oauthModuleResourceServiceImpl;

    @RequestMapping(value = "/menu/user/{userId}", method = RequestMethod.GET)
    public ResponseData<List<OauthModuleResources>> getMenusByUserId(@PathVariable("userId") String userId) {
        log.debug("根据用户查询菜单");
        List<OauthModuleResources> list;
        try {
            list = oauthModuleResourceServiceImpl.getMenusByUserId(userId);
        } catch (Exception e) {
            log.error("根据用户查询菜单错误");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

}
