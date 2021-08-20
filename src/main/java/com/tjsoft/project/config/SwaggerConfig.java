package com.tjsoft.project.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.tjsoft.project.core.ProjectConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置项
 *
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.config
 * @date 2021/8/18 15:01
 * @Copyright ©
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {



    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)      // 选择swagger2版本
                .apiInfo(apiInfo())         //定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.tjsoft.project.controller"))  // 指定生成api文档的包
                .paths(PathSelectors.any())     // 指定所有路径
                .build()
                ;
    }

    /**
     * 构建文档api信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")     // 文档标题
                .contact(new Contact("sefo", "http://localhost:8080", "414478343@qq.com"))   //联系人信息
                .description("swagger接口文档")      //描述
                .version("1.0.1")     //文档版本号
                .termsOfServiceUrl("http://localhost:8080")     //网站地址
                .build();
    }
}
