package com.petter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author hongxf
 * @since 2017-02-23 14:11
 */
@Entity
public class Demo2 implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String realName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
