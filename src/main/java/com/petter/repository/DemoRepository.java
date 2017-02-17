package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @since 2017-02-10 23:52
 */
public interface DemoRepository extends CrudRepository<Demo, Long> {
}
