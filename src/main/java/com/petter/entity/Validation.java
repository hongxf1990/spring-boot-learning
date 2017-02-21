package com.petter.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hongxf
 * @since 2017-02-21 10:47
 */
public class Validation {

    private Long id;

    @NotEmpty(message = "{validation.name}")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Demo2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
