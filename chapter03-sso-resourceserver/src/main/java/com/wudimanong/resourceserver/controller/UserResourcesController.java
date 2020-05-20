package com.wudimanong.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@RestController
@RequestMapping("/user")
public class UserResourcesController {

    @GetMapping("/getUserNickName")
    public String getUserNickName(@RequestParam("uid") long uid) {
        return "无敌码农";
    }
}
