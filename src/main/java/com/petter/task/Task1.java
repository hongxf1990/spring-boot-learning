package com.petter.task;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 使用@Async注解就能简单的将原来的同步函数变为异步函数
 * 为了让@Async注解能够生效，还需要在Spring Boot的主程序中配置@EnableAsync
 * @author hongxf
 * @since 2017-02-20 14:04
 */
@Component
public class Task1 {
    //定义一个随机对象.
    private static Random random = new Random();

    //任务一;
    public void doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    //任务二;
    public void doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    //任务3;
    public void doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
