package com.petter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

@SpringBootApplication
//@ServletComponentScan
@EnableAsync
@MapperScan(value = "com.petter.mapper") //扫描mybatis的接口
public class BootLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootLearningApplication.class, args);
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
