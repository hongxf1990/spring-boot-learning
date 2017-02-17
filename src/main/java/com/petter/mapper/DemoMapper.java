package com.petter.mapper;

import com.petter.entity.Demo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @since 2017-02-17 21:29
 */
@Repository
public interface DemoMapper {

    @Select("select * from demo where id = #{id}")
    Demo getById(Long id);

    @Select("select * from demo where name like #{name}")
    List<Demo> likeName(String name);

    @Select("select name from demo where id = #{id}")
    String getNameById(String name);
}
