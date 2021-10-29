package com.tjsoft.project.config;

import com.tjsoft.project.prop.MinioProp;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.config
 * @date 2021/10/28 10:08
 * @Copyright ©
 */
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProp minioProp;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient client = new MinioClient(minioProp.getEndpoint(), minioProp.getAccesskey(), minioProp.getSecretKey());
        return client;
    }
}
