package com.chenfangming.backend.service;

import com.chenfangming.backend.persistence.entity.UserEntity;
import com.chenfangming.backend.persistence.mapper.IUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * UserService.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 09:14
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private IUserMapper userMapper;


    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户信息
     */
    public UserEntity selectByName(String name) {
        return userMapper.selectByName(name);
    }

}
