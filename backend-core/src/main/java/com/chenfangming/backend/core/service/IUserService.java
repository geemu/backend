package com.chenfangming.backend.core.service;
import com.chenfangming.backend.core.persistence.entity.User;

/**
 * 用户业务层接口
 * @author fangming.chen
 * @since 2018-08-28 15:11:
 * Email cfmmail@sina.com
 */
public interface IUserService {
    /**
     * 根据用户名获取用户,未删除的用户
     * @param name 用户名
     * @return 用户
     */
    User getByName(String name);
}
