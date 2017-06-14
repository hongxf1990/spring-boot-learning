package com.petter.runner;

import com.petter.util.SpringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在项目服务启动的时候就去加载一些数据或做一些事情
 * 服务启动执行
 * @author Administrator
 * @since 2017-02-12 20:35
 */
@Component
@Order(1)
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        if (applicationContext == null) {
            System.out.println("applicationContext is null");
        }
        System.out.println(">>>>>>>>>>>>>>>服务启动执行11111，执行加载数据等操作<<<<<<<<<<<<<");
    }
}
