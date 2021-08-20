package com.tjsoft.project.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.tjsoft.project.core.ProjectConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.config
 * @date 2021/8/11 16:04
 * @Copyright ©
 */
@Configuration
@MapperScan("com.tjsoft.project.dao")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType(ProjectConstants.DATA_SOURCE_TYPE_MYSQL);
        return page;
    }
}
