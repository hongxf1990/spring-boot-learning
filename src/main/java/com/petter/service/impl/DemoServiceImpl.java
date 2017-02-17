package com.petter.service.impl;

import com.petter.dao.DemoDao;
import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import com.petter.service.IDemoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author Administrator
 * @since 2017-02-10 23:55
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Resource
    private DemoRepository demoRepository;
    @Resource
    private DemoDao demoDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    private static final String DEMO_CACHE_NAME = "demo";

    @Override
    @Transactional
    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    @Cacheable(value = "demo")
    @Override
    public Demo getById(Long id) {
        System.out.println("getById() ==== 从数据库中进行获取的。。。id= " + id);
        return demoRepository.findOne(id);
        //return demoDao.getById(id);
    }

    @Cacheable(value = DEMO_CACHE_NAME, key = "'demo_' + #id")
    @Override
    public Demo findById(Long id) {
        System.out.println("findById() ==== 从数据库中进行获取的。。。id= " + id);
        return demoRepository.findOne(id);
        //return demoDao.getById(id);
    }

    @CacheEvict(value = "demo")
    @Override
    public void deleteFromCache(Long id) {
        System.out.println("demo从缓存中删除.");
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = "'demo_' + #id")
    @Override
    public void deleteFromEhCache(Long id) {
        System.out.println("demo从缓存中删除.");
    }

    @Override
    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisCache", "random1=" + Math.random());
        System.out.println(valueOperations.get("redisCache"));
    }
}
