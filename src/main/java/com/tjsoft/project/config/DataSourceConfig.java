package com.tjsoft.project.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置类
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.config
 * @date 2021/8/11 16:03
 * @Copyright ©
 */
@Configuration
public class DataSourceConfig {

    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){

        return new HikariDataSource();
    }

    /**
     * 配置事物管理器
     */
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(){

        return new DataSourceTransactionManager(dataSource());
    }
}
