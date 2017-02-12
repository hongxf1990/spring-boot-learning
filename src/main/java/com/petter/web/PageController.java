package com.petter.web;

import com.petter.entity.Demo;
import com.petter.environment.MongoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @since 2017-02-12 13:02
 */
@Controller
public class PageController {

    @Resource
    private MongoProperties properties;

    @RequestMapping("/hello-thymeleaf")
    public String helloHtml(
            @ModelAttribute(name = "demo1") Demo demo
    ) {
        System.out.println("host=" + properties.getHost());
        System.out.println("uri=" + properties.getUri());
        demo.setName("hongxf");
        return "hello";
    }

    @RequestMapping("/hello-freemarker")
    public String helloHtml2(
            Demo demo
    ) {
        demo.setName("hongxf2");
        return "hello";
    }
}
