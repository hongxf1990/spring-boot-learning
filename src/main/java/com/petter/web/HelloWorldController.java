package com.petter.web;

import com.petter.bean.DemoBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @since 2017-02-08 22:54
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello-world")
    public String hello(){
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
