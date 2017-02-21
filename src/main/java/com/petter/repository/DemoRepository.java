package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @since 2017-02-10 23:52
 */
public interface DemoRepository extends CrudRepository<Demo, Long> {

    @Query(value = "select d.* from demo d where name = ?1", nativeQuery = true)
    //@Modifying
    Demo findByName(String name);
}
