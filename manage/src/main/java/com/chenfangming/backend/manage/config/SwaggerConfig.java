package com.chenfangming.backend.manage.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger配置，注有@Api的class生成文档，只在Dev环境生效
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:29
 */
@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /*** 是否开启swagger  默认false */
    @Value("${swagger.enable:false}")
    private boolean enable;
    /*** 是否开启swagger  默认false */
    @Value("${swagger.title:Api文档}")
    private String title;
    /*** 是否开启swagger  默认false */
    @Value("${swagger.description:后台管理Api文档}")
    private String description;

    /**
     * 创建Swagger文档
     * @return Docket Docket
     */
    @Bean
    public Docket createApi() {
        log.info(">>>>>>>>>>>>>>>>>>>>初始化:Docket");
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class);
    }

    /**
     * 创建ApiInfo对象
     * @return ApiInfo
     */
    private ApiInfo createApiInfo() {
        log.info(">>>>>>>>>>>>>>>>>>>>创建:Docket");
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .build();
    }
}
