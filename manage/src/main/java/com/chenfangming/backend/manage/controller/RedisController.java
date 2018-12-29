package com.chenfangming.backend.manage.controller;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis操作.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-29 10:15
 */
@Slf4j
@Api(description = "Redis控制器")
@RestController
public class RedisController {
  /** HashOperations. **/
  private HashOperations<Object, Object, Object> hashOperations;
  /** ValueOperations. **/
  private ValueOperations<Object, Object> valueOperations;

  /**
   * 构造器注入.
   * @param hashOperations hashOperations
   * @param valueOperations valueOperations
   */
  public RedisController(HashOperations<Object, Object, Object> hashOperations, ValueOperations<Object, Object> valueOperations) {
    this.hashOperations = hashOperations;
    this.valueOperations = valueOperations;
  }

  @ApiOperation("hash")
  @GetMapping("hash")
  public Object hash(String key, String hashKey, String value) {
    hashOperations.put(key, hashKey, value);
    return hashOperations.entries(key);
  }

  @ApiOperation("value")
  @GetMapping("value")
  public Object value(String key, UserEntity userEntity) {
    valueOperations.set(key, userEntity);
    return valueOperations.get(key);
  }
}
