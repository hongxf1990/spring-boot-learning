package com.petter.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @since 2017-02-12 20:35
 */
@Component
@Order(2)
public class StartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行22222，执行加载数据等操作<<<<<<<<<<<<<");
    }
}
