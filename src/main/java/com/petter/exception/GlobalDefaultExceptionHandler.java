package com.petter.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @since 2017-02-10 20:46
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler
    public void defaultErrorHandler(
            HttpServletRequest req, Exception e
    ) {
        /*
        * 返回json数据或者String数据：
        * 那么需要在方法上加上注解：@ResponseBody
        * 添加return即可。
        */

       /*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
        */
       //所有的异常这里都会处理，后台不会再报错
       e.printStackTrace();
       System.out.println("这里进行了异常处理");
    }
}
