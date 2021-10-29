package com.tjsoft.project.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.config
 * @date 2021/10/28 10:07
 * @Copyright ©
 */
@Data
@ConfigurationProperties(prefix = "minio")
@Component
public class MinioProp {
    private String endpoint;
    private String accesskey;
    private String secretKey;
}
