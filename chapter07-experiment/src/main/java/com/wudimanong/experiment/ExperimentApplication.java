package com.wudimanong.experiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jiangqiao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExperimentApplication.class, args);
    }
}
