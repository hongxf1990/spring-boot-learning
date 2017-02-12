package com.petter.entity;

import javax.persistence.*;

/**
 * @author Administrator
 * @since 2017-02-10 21:57
 */
@Entity
@Table(name = "demo")
public class Demo {

    @Id
    //@GenericGenerator(name = "test", strategy = "uuid")
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
