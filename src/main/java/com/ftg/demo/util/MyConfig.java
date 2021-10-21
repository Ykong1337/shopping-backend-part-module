package com.ftg.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.ftg.demo.mapper")
public class MyConfig {

    @Bean
    public DataSource initDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("ykong");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/data");
        return driverManagerDataSource;
    }

    @Bean
    public PaginationInterceptor initMybatisPage() {
        PaginationInterceptor pi = new PaginationInterceptor();
        pi.setDbType(DbType.MYSQL);
        return pi;
    }

    @Bean
    public MybatisSqlSessionFactoryBean initMybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean msf = new MybatisSqlSessionFactoryBean();
        msf.setDataSource(this.initDataSource());
        msf.setPlugins(this.initMybatisPage());
        return msf;
    }
}
