package com.petter.service.impl;

import com.petter.entity.UserInfo;
import com.petter.repository.UserInfoRepository;
import com.petter.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-02-17 14:04
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService{

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
