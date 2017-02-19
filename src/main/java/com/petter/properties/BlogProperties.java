package com.petter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 其他地方使用时使用@EnableConfigurationProperties({BlogProperties.class})就行
 * @author Administrator
 * @since 2017-02-19 19:13
 */
@ConfigurationProperties(prefix = "com.petter.blog")
public class BlogProperties {
    private String name;//博客作者

    private String title;//博客标题

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
}
