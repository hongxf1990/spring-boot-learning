package com.petter.service.impl;

import com.petter.service.IEmailService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Profile("dev")表明只有Spring定义的Profile为dev时才会实例化DevEmailService这个类
 * @author Administrator
 * @since 2017-02-19 19:24
 */
@Service
@Profile("dev") //开发环境
public class DevEmailServiceImpl implements IEmailService {
    @Override
    public void send() {
        System.out.println("DevEmailServiceImpl.send().开发环境不执行邮件的发送.");
    }
}
