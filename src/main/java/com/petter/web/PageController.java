package com.petter.web;

import com.petter.entity.Demo;
import com.petter.properties.MongoProperties;
import com.petter.util.LocaleMessageSourceUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author Administrator
 * @since 2017-02-12 13:02
 */
@Controller
public class PageController {

    @Resource
    private MongoProperties properties;
    @Resource
    private MessageSource messageSource;
    @Resource
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello3";
    }

    @RequestMapping("/hello-thymeleaf")
    public String helloHtml(
            //HttpServletRequest request,
            @ModelAttribute(name = "demo1") Demo demo
    ) {
        //System.out.println("host=" + properties.getHost());
        //System.out.println("uri=" + properties.getUri());
        //代码中使用i18n
        Locale locale = LocaleContextHolder.getLocale(); //常用
        //Locale locale1 = RequestContextUtils.getLocale(request);
        String welcome = messageSource.getMessage("welcome", null, locale);
        System.out.println(welcome);
        String welcome1 = localeMessageSourceUtil.getMessage("welcome");
        System.out.println(welcome1);
        demo.setName("hongxf");
        return "hello-thy";
    }

    @RequestMapping("/hello-freemarker")
    public String helloHtml2(
            Demo demo
    ) {
        demo.setName("hongxf2");
        return "hello";
    }

    @RequestMapping(value = "/changeSessionLanguage", method = RequestMethod.GET)
    public String changeSessionLanguage(HttpServletRequest request,
                                        HttpServletResponse response, String lang){
        System.out.println(lang);
        //if("zh".equals(lang)){
        //    //代码中即可通过以下方法进行语言设置
        //    request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
        //            new Locale("zh", "CN"));
        //} else if("en".equals(lang)){
        //    //代码中即可通过以下方法进行语言设置
        //    request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
        //            new Locale("en", "US"));
        //}
        //return "redirect:/hello-thymeleaf";

        //不管是SessionLocaleResolver 还是 CookieLocaleResolver 都可以
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if("en".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/hello-thymeleaf";
    }
}
