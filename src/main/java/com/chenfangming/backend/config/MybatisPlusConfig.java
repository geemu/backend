package com.chenfangming.backend.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus配置
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 21:50
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {

    /**
     * MyBatis Plus分页插件配置
     * @return 分页插件拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("初始化:PaginationInterceptor");
        return new PaginationInterceptor();
    }

}
