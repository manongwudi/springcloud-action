package com.wudimanong.monitor.controller;

import com.wudimanong.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorServiceImpl;

    @GetMapping("/test")
    public String monitorTest(@RequestParam("name") String name) {
        monitorServiceImpl.monitorTest(name);
        return "监控示范工程测试接口返回->OK!";
    }
}
