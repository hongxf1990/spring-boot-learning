package com.petter.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hongxf
 * @since 2017-02-23 15:56
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     *
     */
    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("swagger-ui.html")
    //            .addResourceLocations("classpath:/META-INF/resources/");
    //
    //    registry.addResourceHandler("/webjars/**")
    //            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    //}

    @Bean
    public Docket testApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(Predicates.or(PathSelectors.regex("/demo/.*")))//过滤的接口
                .build()
                .apiInfo(demoApiInfo());
    }

    private static final Contact DEFAULT_CONTACT = new Contact("hongxf", "https://github.com/hongxf1990", "hongxf19900420@gmail.com");
    //public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
    //        DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

    private ApiInfo testApiInfo() {
        return new ApiInfo(
                "Test相关接口",
                "Test相关接口，主要用于测试",
                "1.0.0",
                "http://412887952-qq-com.iteye.com",
                DEFAULT_CONTACT,
                "上海宠物港湾网站",
                "www.petter.com");
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfo(
                "Demo相关接口",
                "Demo相关接口，获取个数，获取列表",
                "1.0.0",
                "",
                DEFAULT_CONTACT,
                "上海宠物港湾网站",
                "www.petter.com");
    }

    //private ApiInfo testApiInfo() {
    //
    //    return new ApiInfo("Test相关接口",//大标题
    //            "Test相关接口，主要用于测试.",//小标题
    //            "1.0",//版本
    //            "http://412887952-qq-com.iteye.com/",
    //            "Angel",//作者
    //            "北京知远信息科技有限公司",//链接显示文字
    //            "http://www.kfit.com.cn/"//网站链接
    //    );
    //}

    //private ApiInfo demoApiInfo() {
    //    return new ApiInfo("Demo相关接口",//大标题
    //            "Demo相关接口，获取个数，获取列表，注意：",//小标题
    //            "1.0",//版本
    //            "http://412887952-qq-com.iteye.com/",
    //            "Angel",//作者
    //            "北京知远信息科技有限公司",//链接显示文字
    //            "http://www.kfit.com.cn/"//网站链接
    //    );
    //}
}
