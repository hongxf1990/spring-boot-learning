package com.petter.scheduling.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web应用启动时，注册已经确定的定时任务
 * 一些动态的、未确定触发时间的定时任务，后续可通过静态的Scheduler注册。
 * @author hongxf
 * @since 2017-06-19 15:27
 */
//@WebListener 这里配置这个没有用需要使用ServletListenerRegistrationBean进行注册
public class ApplicationContextListener implements ServletContextListener {

    private Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            System.out.println("scheduler.start");
            System.out.println("schedulerName-----" + scheduler.getSchedulerName());

            //具体任务.
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();

            //相对于其他方式定义定时任务的触发时间，我们较常用Cron Schedule:
            //触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .withSchedule(cronScheduleBuilder).build();

            // 交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            scheduler.shutdown();
            System.out.println("scheduler.shutdown");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }//关闭定时任务调度器.
    }
}
