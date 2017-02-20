package com.petter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果配置了Component则其他地方可以直接使用，不需要使用@EnableConfigurationProperties({BlogProperties.class})
 * 相反则需要
 *
 * @author Administrator
 * @since 2017-02-19 19:13
 */
@ConfigurationProperties(prefix = "com.petter.blog")
@Component
public class BlogProperties {
    private String name;//博客作者

    private String title;//博客标题

    private List<String> authors = new ArrayList<>(); //属性名称authors需要和application.properties文件的key是对应的。

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
