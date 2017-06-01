package com.petter.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态添加修改和删除定时任务
 * @author hongxf
 * @since 2017-06-01 14:14
 */
@Component
@RestController
public class DynamicTask {

    //线程池任务调度类，能够开启线程池进行任务调度
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    //ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，
    //在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
    //在ScheduledFuture中有一个cancel可以停止定时任务
    private ScheduledFuture<?> future;

    //添加一个定时任务
    @RequestMapping("/startCron")
    public String startCron() {
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron()");
        return "startCron";
    }

    //停止删除一个定时任务
    @RequestMapping("/stopCron")
    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

    //修改cron
    @RequestMapping("/changeCron10")
    public String startCron10() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

    //修改cron 第二种方法（没用）
    @RequestMapping("/startCron15")
    public String startCron15() {
        threadPoolTaskScheduler.schedule(new MyRunnable(), triggerContext -> new CronTrigger("*/15 * * * * *").nextExecutionTime(triggerContext));
        System.out.println("DynamicTask.startCron15");
        return "startCron15";
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("DynamicTask.MyRunnable.run()，" + new Date());
        }
    }
}
