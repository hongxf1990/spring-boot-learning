package com.petter.scheduling.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * 定义一个任务类，实现Job类
 * @author hongxf
 * @since 2017-06-19 14:56
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //执行响应的任务
        System.out.println("HelloJob.execute " + LocalDateTime.now());
    }
}
