package com.petter.dao;

import com.petter.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @since 2017-02-11 10:14
 */
@Repository
public class DemoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过id获取demo对象.
     * @param id
     * @return
     */
    public Demo getById(long id){
        String sql = "select * from Demo where id=?";
        RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<>(Demo.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
