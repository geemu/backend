package com.chenfangming.backend.manage.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DozerConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 13:33
 */

@Configuration
public class DozerConfig {

  @Bean
  public DozerBeanMapper dozer() {
    return new DozerBeanMapper();
  }
}
