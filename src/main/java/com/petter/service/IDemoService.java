package com.petter.service;

import com.petter.entity.Demo;

import java.util.List;

/**
 * @author hongxf
 * @since 2017-02-14 9:52
 */
public interface IDemoService {

    void save(Demo demo);

    Demo getById(Long id);

    Demo findById(Long id);

    void deleteFromCache(Long id);

    void deleteFromEhCache(Long id);

    void test();

    List<Demo> likeName(String name);
}
