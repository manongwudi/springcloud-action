package com.wudimanong.oauth.resource.controller;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthClientDetails;
import com.wudimanong.oauth.resource.service.OauthClientDetailsService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@Slf4j
@RestController
@RequestMapping(value = "/internel")
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /**
     * 获取所有客户端信息方法
     *
     * @return
     */
    @RequestMapping(value = "/client/all", method = RequestMethod.GET)
    public ResponseData<List<OauthClientDetails>> getAllClient() {
        List<OauthClientDetails> list = null;
        try {
            list = oauthClientDetailsService.selectAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }
}
