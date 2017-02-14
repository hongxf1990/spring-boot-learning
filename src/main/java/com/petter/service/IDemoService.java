package com.petter.service;

import com.petter.entity.Demo;

/**
 * @author hongxf
 * @since 2017-02-14 9:52
 */
public interface IDemoService {

    void save(Demo demo);

    Demo getById(Long id);

    void deleteFromCache(Long id);

    void test();
}
