package com.petter.repository;

import com.petter.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongxf
 * @since 2017-02-17 13:37
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}
