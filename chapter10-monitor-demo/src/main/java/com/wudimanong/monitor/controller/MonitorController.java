package com.wudimanong.monitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @GetMapping("/test")
    public String monitorTest() {
        return "监控示范工程测试接口返回->OK!";
    }
}
