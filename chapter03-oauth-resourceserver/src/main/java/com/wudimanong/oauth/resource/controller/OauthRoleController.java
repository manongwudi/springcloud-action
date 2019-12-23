package com.wudimanong.oauth.resource.controller;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthRole;
import com.wudimanong.oauth.resource.service.OauthRoleService;
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
public class OauthRoleController {

    @Autowired
    OauthRoleService oauthRoleServiceImpl;

    @RequestMapping(value = "/role/user/{userId}", method = RequestMethod.GET)
    public ResponseData<List<OauthRole>> getRoleByUserId(@PathVariable("userId") String userId) {
        log.debug("根据用户查询角色");
        List<OauthRole> list;
        try {
            list = oauthRoleServiceImpl.getRoleByUserId(userId);
        } catch (Exception e) {
            log.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }
}
