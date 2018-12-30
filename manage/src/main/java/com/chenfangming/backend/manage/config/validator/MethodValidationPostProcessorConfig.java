package com.chenfangming.backend.manage.config.validator;

import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Spring扩展的 支持方法参数校验的配置.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-29 11:33
 */
@Slf4j
@Configuration
public class MethodValidationPostProcessorConfig {
  /** Validator. **/
  private Validator validator;

  /**
   * 构造器注入.
   * @param validator validator
   */
  public MethodValidationPostProcessorConfig(Validator validator) {
    this.validator = validator;
  }

  /**
   * Spring validator支持对方法的校验.
   * @return MethodValidationPostProcessor
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    log.info("初始化:MethodValidationPostProcessor");
    MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    postProcessor.setValidator(validator);
    return postProcessor;
  }
}
