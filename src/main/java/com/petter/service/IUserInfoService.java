package com.petter.service;

import com.petter.entity.UserInfo;

/**
 * @author hongxf
 * @since 2017-02-17 13:40
 */
public interface IUserInfoService {

    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}
