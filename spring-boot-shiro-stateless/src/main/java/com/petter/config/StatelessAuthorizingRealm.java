package com.petter.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 1、客户端生成的消息摘要；
 * 2、客户端传入的用户身份；
 * 3、客户端请求的参数列表；
 * 4、生成无状态Token
 * 5、委托给Realm进行登录
 * @author hongxf
 * @since 2017-02-28 15:40
 */
public class StatelessAuthorizingRealm extends AuthorizingRealm {

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAccessControlFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAccessControlFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("StatelessAuthorizingRealm.doGetAuthorizationInfo()");
        //根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //这里模拟admin账号才有role的权限.
        if("admin".equals(username)){
            authorizationInfo.addRole("admin");
        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        StatelessAuthenticationToken statelessToken = (StatelessAuthenticationToken)token;
        String username = (String)statelessToken.getPrincipal();//不能为null,否则会报错的.

        //根据用户名获取密钥（和客户端的一样）
        String key = getKey(username);

        //在服务器端生成客户端参数消息摘要
        String serverDigest = HmacSHA256Utils.digest(key, statelessToken.getParams());
        System.out.println(statelessToken.getClientDigest());
        System.out.println(serverDigest);
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,
                serverDigest,
                getName());
        return authenticationInfo;
    }

    //得到密钥，此处硬编码一个.
    private String getKey(String username) {
        return "andy123456";
    }

}
