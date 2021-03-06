package com.petter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

@SpringBootApplication
//@ServletComponentScan  //用于@WebServlet @WebListener @WebFilter注解时候放开才能进行注册
@EnableAsync
@MapperScan(value = "com.petter.mapper") //扫描mybatis的接口
public class BootLearningApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BootLearningApplication.class, args);
        String[] beanNames = ctx.getBeanNamesForAnnotation(Repository.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        //获取context.  -- Angel -守护天使
        //ApplicationContext ctx =  (ApplicationContext) SpringApplication.run(BootLearningApplication.class, args);
        //获取BeanFactory
        //DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
        //创建bean信息.
        //BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
        //beanDefinitionBuilder.addPropertyValue("name","张三");
        //动态注册bean.
        //defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
        //获取动态注册的bean.
        //TestService testService =ctx.getBean(TestService.class);、testService.print();
    }

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//设置文件大小限制,超过了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("128KB"); //KB,MB
		//设置总上传数据总大小
		factory.setMaxRequestSize("256KB");
		//Sets the directory location where files will be stored.
		//factory.setLocation("路径地址");
		return factory.createMultipartConfig();
	}

	//SessionLocaleResolver
    //@Bean
    //public LocaleResolver localeResolver() {
     //   SessionLocaleResolver slr = new SessionLocaleResolver();
     //   //设置默认区域
     //   slr.setDefaultLocale(Locale.CHINA);
     //   return slr;
    //}

	//CookieLocaleResolver
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver slr = new CookieLocaleResolver();
		//设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		slr.setCookieMaxAge(3600);//设置cookie有效期.
		return slr;
	}
}
