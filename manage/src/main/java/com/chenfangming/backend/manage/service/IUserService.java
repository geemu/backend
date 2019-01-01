package com.chenfangming.backend.manage.service;

import com.chenfangming.backend.manage.domain.response.FindByNameResponse;

/**
 * IUserService.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 09:14
 */
public interface IUserService {

  /**
   * 根据用户名查询用户.
   * @param userName 用户名
   * @return 用户信息
   */
  FindByNameResponse findByName(String userName);

}
