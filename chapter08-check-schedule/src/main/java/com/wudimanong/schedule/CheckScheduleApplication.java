package com.wudimanong.schedule;

import com.wudimanong.schedule.job.TestJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jiangqiao
 */

@EnableDiscoveryClient
@SpringBootApplication
public class CheckScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckScheduleApplication.class, args);

        //new ScheduleJobBootstrap(createRegistryCenter(), new TestJob(), createJobConfiguration()).schedule();
    }

   /* private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("10.211.55.12:2181,10.211.55.12:2182,10.211.55.12:2183", "check-schedule"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration() {
        // 创建作业配置
        // ...
        JobConfiguration jobConfig = JobConfiguration.newBuilder("TestJob", 3).cron("0/1 * * * * ?").build();
        return jobConfig;
    }*/
}
