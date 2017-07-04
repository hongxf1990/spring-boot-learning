package com.petter.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.petter.properties.DatabaseProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 手动注册servlet和filter，不需要@ServletComponentScan 注解
 * @author Administrator
 * @since 2017-02-12 11:34
 */
@Configuration
@EnableTransactionManagement //开启事务
public class DruidConfig {

    @Resource
    private DatabaseProperties databaseProperties;
    /**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet2(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid2/*");

        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin2");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    //@Bean
    //public ServletRegistrationBean TestServlet() {
    //    return new ServletRegistrationBean(new TestServlet(), "/test-servlet");
    //}

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }

    /**

     * 注册dataSource，这里作为一个例子，只注入了部分参数信息，其它的参数自行扩散思维。
     * 在application.properties配置的方式是比较好，如果有特殊需求的话，也可以在这里进行注入
     * 如果同时进行了编程式的注入和配置的注入，配置的就无效了。
     * 如果采用第三方数据源需要把各个参数配置进来，必须采用这种方式
     * @return
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(databaseProperties.getDriverClassName());
        druidDataSource.setUrl(databaseProperties.getUrl());
        druidDataSource.setUsername(databaseProperties.getUsername());
        druidDataSource.setPassword(databaseProperties.getPassword());
        druidDataSource.setInitialSize(databaseProperties.getInitialSize());
        druidDataSource.setMaxActive(databaseProperties.getMaxActive());

        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(druidDataSource());
    }
}
