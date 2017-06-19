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
     * 这个方法每20秒打印一次.
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
    public void task2(){
        System.out.println("MyTask.tast2()," + new Date());
    }

    /**
     * 固定等待时间@Scheduled(fixedDelay = 时间间隔 )，不管线程任务的执行时间，每次都要把任务执行完成后再延迟固定时间后再执行下一次。
     * ①程序启动的时候，会执行第一次；
     * ②第二次执行的前提是上一次任务执行完毕之后才会执行。
     * ③如果，间隔时间>=程序执行时间，每次时间差=间隔时间+程序执行时间}
     * ④如果，间隔时间<程序执行时间，时间差可能是=程序执行时间（这是大部分的情况）；也可能是=间隔时间+程序执行时间；
     */
    @Scheduled(fixedDelay = 3000)
    public void task3(){
        Date d = new Date();
        System.out.println("MyTask.task3()," + d);
        try {
            Thread.sleep(6000);
            System.out.println("MyTask.task3(),"+ d +".end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 固定间隔时间@Scheduled(fixedRate = 时间间隔 )，是以固定频率来执行线程任务。
     * (a)程序启动之后，就会启动第一次；
     * (b)如果，间隔时间>=程序执行时间，每次时间差=间隔时间；
     * (c)如果，间隔时间<程序执行时间，每次时间差=间隔时间+程序执行时间；
     */
    @Scheduled(fixedRate  = 3000)
    public void task4(){
        Date d = new Date();
        System.out.println("MyTask.task4(),"+d);
        try {
            Thread.sleep(2000);
            System.out.println("MyTask.task4(),"+d+".end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
