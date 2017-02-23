package com.petter.web;

import com.github.pagehelper.PageHelper;
import com.petter.entity.Demo;
import com.petter.service.IDemoService;
import com.petter.task.Task1;
import com.petter.task.Task2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @since 2017-02-10 23:58
 */
@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private IDemoService demoService;
    @Resource
    private Task1 task1;
    @Resource
    private Task2 task2;

    /**
     * 测试保存数据方法.
     * @return
     */
    @ApiIgnore//使用该注解忽略这个API
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
        logger.info("使用spring boot自带的配置，info");
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
        PageHelper.startPage(1, 2);
        return demoService.likeName(name);
    }

    @RequestMapping(value = "/task1")
    public String task1() throws Exception {
        task1.doTaskOne();
        task1.doTaskTwo();
        task1.doTaskThree();
        return "task1";
    }

    @RequestMapping(value = "/task2")
    public String task2() throws Exception {
        task2.doTaskOne();
        task2.doTaskTwo();
        task2.doTaskThree();
        return "task2";
    }

    @RequestMapping(value = "/name")
    public Demo getByName(String name) {
        return demoService.getByName(name);
    }
}
