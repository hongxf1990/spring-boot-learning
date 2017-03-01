package com.petter.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongxf
 * @since 2017-02-28 11:17
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String params1, String params2) {
        return "hello, hongxf, params1=" + params1 + ", params2=" + params2;
    }

    /**
     * 此方法执行的时候，会抛出异常：
     * Session creation has been disabled for the current subject.
     */
    @RequestMapping("/hello3")
    public String hello3(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        System.out.println(session);
        return "hello3,Andy";
    }
}
