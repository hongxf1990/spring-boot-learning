package com.petter.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.annotation.Resource;

/**
 * 采用MD5方式实现去除浏览器缓存静态文件产生的问题
 * 注意使用md5文件名方式的时候，Spring 是有缓存机制的，也就是说，
 * 在服务不重启的情况下，你去变动修改这些资源文件，其文件名的md5值并不会改变，只有重启服务再次访问才会生效。
 * @author Administrator
 * @since 2017-02-26 15:39
 */
@ControllerAdvice
public class ResourceUrlProviderController {

    @Resource
    private ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
}
