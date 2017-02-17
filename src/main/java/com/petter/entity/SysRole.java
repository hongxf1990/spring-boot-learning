package com.petter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-02-17 11:22
 */
@Entity
@Table(name = "role")
public class SysRole implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id; // 编号

    @Column(name = "role")
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    @Column(name = "description")
    private String description;  // 角色描述,UI界面显示使用

    @Column(name = "available")
    private Boolean available  = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<SysRolePermission> permissionList;  //角色 -- 权限关系：多对多关系;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfoList;   // 用户 - 角色关系定义; 一个角色对应多个用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRolePermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysRolePermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
