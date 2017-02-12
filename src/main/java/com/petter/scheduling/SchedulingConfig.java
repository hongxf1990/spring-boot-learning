package com.petter.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Administrator
 * @since 2017-02-12 0:21
 */
//@Configuration
//@EnableScheduling
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
