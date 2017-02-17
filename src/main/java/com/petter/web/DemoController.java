package com.petter.web;

import com.petter.entity.Demo;
import com.petter.service.IDemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @since 2017-02-10 23:58
 */
@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    @Resource
    private IDemoService demoService;

    /**
     * 测试保存数据方法.
     * @return
     */
    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("hongxf");
        demoService.save(d);//保存数据.
        return "ok.DemoController.save";
    }

    @RequestMapping("/{id}")
    public Demo getById(
            @PathVariable long id
    ){
        return demoService.getById(id);
    }

    @RequestMapping("/test1")
    public String test1() {
        demoService.test();
        return "ok";
    }

    @RequestMapping("/test")
    public Demo test(){
        Demo loaded = demoService.getById(1L);
        System.out.println("loaded ="+loaded);
        Demo cached = demoService.getById(1L);
        System.out.println("cached ="+cached);
        loaded = demoService.getById(2L);
        System.out.println("loaded2 ="+loaded);
        return loaded;
    }

    @RequestMapping("/test-ehcache")
    public Demo testEhcache(){
        Demo loaded = demoService.findById(1L);
        System.out.println("loaded= "+loaded);
        Demo cached = demoService.findById(1L);
        System.out.println("cached= "+cached);
        loaded = demoService.findById(2L);
        System.out.println("loaded2= "+loaded);
        return loaded;
    }

    @RequestMapping("/delete")
    public String delete(long id){
        demoService.deleteFromCache(id);
        return "ok";
    }

    @RequestMapping("/delete-ehcache")
    public String deleteFromEhcache(long id){
        demoService.deleteFromEhCache(id);
        return "ok";
    }

    @RequestMapping("/likeName")
    public List<Demo> likeName(String name){
        return demoService.likeName(name);
    }
}
