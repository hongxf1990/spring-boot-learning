package com.petter.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hongxf
 * @since 2017-02-17 15:19
 */
@RequestMapping("/user-info")
@Controller
public class UserInfoController {

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/user-list")
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/user-add")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){
        return "userInfoAdd";
    }
}
