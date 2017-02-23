package com.petter.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * jersey
 * @author hongxf
 * @since 2017-02-23 16:44
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RequestContextFilter.class);
        //配置restful package.
        packages("com.petter.rest");
    }
}
