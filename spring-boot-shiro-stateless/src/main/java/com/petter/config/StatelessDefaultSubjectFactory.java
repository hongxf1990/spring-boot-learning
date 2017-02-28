package com.petter.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 通过调用context.setSessionCreationEnabled(false)表示不创建会话；
 * 如果之后调用Subject.getSession()将抛出DisabledSessionException异常。
 * @author hongxf
 * @since 2017-02-28 11:29
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //关闭session的创建
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
