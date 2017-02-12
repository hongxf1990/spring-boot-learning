package com.petter.environment;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @since 2017-02-12 22:00
 */
@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoAutoConfiguration {

    @Resource
    private MongoProperties properties;
}
