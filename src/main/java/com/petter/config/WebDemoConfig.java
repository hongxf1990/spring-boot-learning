package com.petter.config;

import com.petter.servlet.ValidateCodeServlet;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 * @since 2017-02-12 0:10
 */
@Configuration
public class WebDemoConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //可以多次使用 addResourceLocations 添加目录，优先级先添加的高于后添加的。
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }

    /**
     *  只有经过DispatcherServlet 的请求，才会走拦截器链
     *  自定义的Servlet请求是不会被拦截的，比如自定义的Servlet地址http://localhost:8080/test-servlet是不会被拦截器拦截的。
     *  并且不管是属于哪个Servlet只要符合过滤器的过滤规则，过滤器都会拦截。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        //registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public ServletRegistrationBean validateCodeServlet() {
        return new ServletRegistrationBean(new ValidateCodeServlet(), "/validateCodeServlet");
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
        // our rest resources will be available in the path /rest/*
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }

    /**
     * setUseSuffixPatternMatch(boolean useSuffixPatternMatch)：
     * 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
     * 当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
     * 当此参数设置为false的时候，那么只能访问/user
     *
     * setUseTrailingSlashMatch(boolean useSuffixPatternMatch)：
     * 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     * 当此参数设置为true的后，那么地址/user，/user/都能正常访问。
     * 当此参数设置为false的时候，那么就只能访问/user了。
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    /**
     * (406)Could not find acceptable representation原因及解决方法
     * 比如通过/login.html请求/login本应该返回json数据，系统会根据html去找html，类型不匹配报错
     * favorPathExtension表示支持后缀匹配，
     * 属性ignoreAcceptHeader默认为false，表示accept-header匹配
     * defaultContentType开启默认匹配。
     * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
     * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    /**
     * 配置使用fastjson取代jackson，看自己的使用习惯了
     * @param converters
     */
    //@Override
    //public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //    super.configureMessageConverters(converters);
    //    //FastJsonHttpMessageConverter 用于Spring MVC4.2以下，FastJsonHttpMessageConverter4用于4.2以上
    //    FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
    //    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    //    fastConverter.setFastJsonConfig(fastJsonConfig);
    //
    //    converters.add(fastConverter);
    //}
}
