package com.chenfangming.backend.manage.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置参数
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-10 15:47
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /** 是否启用 **/
    private boolean enable = false;
}
