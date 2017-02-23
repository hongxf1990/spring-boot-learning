package com.petter.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hongxf
 * @since 2017-02-23 16:48
 */
@Path("/")  //必须添加，否则报404错误
@Component
public class RestResource {

    //使用@Path进行指定访问路径，使用@GET标注是get请求，使用@Produces指定的返回的数据类型
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Map<String,Object> hello() {
        Map<String,Object> map = new HashMap<>();
        map.put("code","1");
        map.put("codeMsg", "success");
        return map;
    }
}
