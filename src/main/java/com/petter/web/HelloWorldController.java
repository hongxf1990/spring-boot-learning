package com.petter.web;

import com.petter.bean.DemoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @since 2017-02-08 22:54
 */
@RestController
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello-world")
    public String hello(){
        logger.info("test hello world");
        return "Hello world!";
    }

    @RequestMapping("/demo")
    public DemoBean getDemo() {
        DemoBean demoBean = new DemoBean();
        demoBean.setId(1L);
        demoBean.setName("Hongxf1");
        return demoBean;
    }

    @RequestMapping("/zero-exception")
    public int zeroException(){
        return 100/0;
    }
}
