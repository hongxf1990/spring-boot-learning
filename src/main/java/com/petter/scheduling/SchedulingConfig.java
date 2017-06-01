package com.petter.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Administrator
 * @since 2017-02-12 0:21
 */
//@Configuration
//@EnableScheduling
public class SchedulingConfig {

    /**
     * 我们希望这个方法每20秒打印一次.
     * cron: 定时任务表达式.
     *
     * 指定：秒，分钟，小时，日期，月份，星期，年（可选）.
     * *：任意.
     *
     */
    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }

    /**
     * 我们希望这个方法每1分钟打印一次.
     */
    @Scheduled(cron="0 0/1 * * * *")
    public void tast2(){
        System.out.println("MyTask.tast2()," + new Date());
    }
}
