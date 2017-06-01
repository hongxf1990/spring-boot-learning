package com.petter.scheduling;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * 定时任务动态修改cron参数
 * @author hongxf
 * @since 2017-06-01 13:52
 */
//@Component
//@EnableScheduling
public class TaskCronChange implements SchedulingConfigurer {

    private static String cron;

    //外部修改
    public static void setCron(String cron) {
        TaskCronChange.cron = cron;
    }

    public TaskCronChange() {
        //默认是：每5秒执行一次
        cron = "0/5 * * * * *";
        new Thread(() -> {
            try {
                //休眠15秒
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //修改为：每10秒执行一次
            cron = "0/10 * * * * *";
            System.err.println("cron change to:" + cron);
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //任务逻辑代码部分.
                System.out.println("TaskCronChange task is running ... " + new Date());
            }
        };

        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //任务触发，可修改任务的执行周期.这里是用一个线程进行自动修改了
                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        };
        //两个参数，一个Runnable,一个是Trigger，在Runnable中执行业务逻辑代码，在Trigger修改定时任务的执行周期。
        taskRegistrar.addTriggerTask(task, trigger);
    }

}
