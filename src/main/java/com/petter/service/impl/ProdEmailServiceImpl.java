package com.petter.service.impl;

import com.petter.service.IEmailService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @since 2017-02-19 19:27
 */
@Service
@Profile("prod") //生产环境.
public class ProdEmailServiceImpl implements IEmailService {
    @Override
    public void send() {
        System.out.println("DevEmailServiceImpl.send().生产环境执行邮件的发送.");
        //具体的邮件发送代码.
        //mail.send();
    }
}
