package com.petter.web;

import com.petter.entity.Demo;
import com.petter.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @since 2017-02-10 23:58
 */
@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    /**
     * 测试保存数据方法.
     * @return
     */
    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);//保存数据.
        return "ok.DemoController.save";
    }

    @RequestMapping("/{id}")
    public Demo getById(
            @PathVariable long id
    ){
        return demoService.getById(id);
    }
}
