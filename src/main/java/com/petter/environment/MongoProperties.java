package com.petter.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ConfigurationProperties注解可以用来配置自定义配置文件
 * @author Administrator
 * @since 2017-02-12 22:01
 */
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {
    private String host;
    private int port;
    private String uri = "mongodb://localhost/test";  //如果不配置则此为默认值
    private String database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
