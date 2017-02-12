package com.petter.service;

import com.petter.dao.DemoDao;
import com.petter.dao.DemoRepository;
import com.petter.entity.Demo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author Administrator
 * @since 2017-02-10 23:55
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;
    @Resource
    private DemoDao demoDao;

    @Transactional
    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    public Demo getById(Long id) {
        //return demoRepository.findOne(id);
        return demoDao.getById(id);
    }
}
