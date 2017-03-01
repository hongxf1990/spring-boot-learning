package com.petter.config;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 访问控制过滤器
 * @author hongxf
 * @since 2017-02-28 15:28
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     *
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     *
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，会往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter("digest");

        //2、客户端传入的用户身份
        String username = request.getParameter("username");

        //3、客户端请求的参数列表
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove("digest");//为什么要移除呢？签名或者消息摘要算法的时候不能包含digest.

        //4、生成无状态Token
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(username, params, clientDigest);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            e.printStackTrace();
            //6、登录失败
            onLoginFail(response);
            return false;//就直接返回给请求者.
        }
        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}
