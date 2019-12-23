package com.wudimanong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joe
 */
@RestController
@RequestMapping("/iot")
public class IotCallBackController {

    //引入Redis客户端操作对象
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/unLockCallBack", method = RequestMethod.POST)
    public boolean unLockCallBack(@RequestParam(value = "thingName") String thingName,
            @RequestParam(value = "requestId") String requestId) {
        //生成监听频道Key
        String key = "IOT_" + thingName + "_" + requestId;
        //模拟实现消息回调
        stringRedisTemplate.convertAndSend(key, "this is a redis callback");
        return true;
    }
}
