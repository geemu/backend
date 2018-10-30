package com.chenfangming.backend.manage.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus配置
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 21:50
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * MyBatis Plus分页插件配置
     * @return 分页插件拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
