package com.tjsoft.project;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.listener.ApplicationStartedEventListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
@Log4j2
@NacosPropertySource(dataId = "application-dev.yml", autoRefreshed = true)
public class ProjectApplication {

    public static void main(String[] args) {

//        SpringApplication.run(ProjectApplication.class, args);

        SpringApplication app = new SpringApplication(ProjectApplication.class);
        //该监听器为了log4j2-spring.xml读取配置文件内容
        Set<ApplicationListener<?>> ls = app.getListeners();
        ApplicationStartedEventListener asel = new ApplicationStartedEventListener();
        app.addListeners(asel);
        app.run(args);
    }

}
