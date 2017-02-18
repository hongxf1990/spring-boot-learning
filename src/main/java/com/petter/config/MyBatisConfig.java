package com.petter.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Administrator
 * @since 2017-02-17 22:05
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        //4.0.0以后版本可以不设置该参数
        props.setProperty("dialect", "mysql");
        //该参数默认为false 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用和startPage中的pageNum效果一样
        props.setProperty("offsetAsPageNum", "true");
        //该参数默认为false 设置为true时，使用RowBounds分页会进行count查询
        props.setProperty("rowBoundsWithCount", "false");
        //设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果,相当于没有执行分页查询，但是返回结果仍然是Page类型
        props.setProperty("pageSizeZero", "true");
        //3.3.0版本可用 - 分页参数合理化，默认false禁用
        //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "none");
        props.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    //sqlsessionFactory 底层有默认的配置。
    //@Bean
    //public SqlSessionFactory sqlSessionFactory() throws Exception {
    //    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    //    //sqlSessionFactoryBean.setDataSource(druidDataSource);
    //    //mybatis分页
    //    PageHelper pageHelper = new PageHelper();
    //    Properties props = new Properties();
    //    props.setProperty("dialect", "mysql");
    //    props.setProperty("offsetAsPageNum", "true");
    //    props.setProperty("rowBoundsWithCount", "false");
    //    props.setProperty("pageSizeZero", "true");
    //    props.setProperty("reasonable", "true");
    //    props.setProperty("supportMethodsArguments", "true");
    //    props.setProperty("returnPageInfo", "none");
    //    props.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
    //    pageHelper.setProperties(props);
    //    //添加插件
    //    sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
    //    //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //    //sqlSessionFactoryBean.setTypeAliasesPackage("com.petter.mapper.entity");
    //    //sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
    //    return sqlSessionFactoryBean.getObject();
    //}
}
