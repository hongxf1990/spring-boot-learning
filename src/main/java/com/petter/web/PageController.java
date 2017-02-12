package com.petter.web;

import com.petter.entity.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @since 2017-02-12 13:02
 */
@Controller
public class PageController {

    @RequestMapping("/hello-thymeleaf")
    public String helloHtml(
            @ModelAttribute(name = "demo1") Demo demo
    ) {
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
