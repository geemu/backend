package com.chenfangming.backend.manage.service;

import com.chenfangming.backend.manage.domain.response.FindByNameResponse;
import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.persistence.mapper.IUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * UserService.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 09:14
 */
@Slf4j
@Service
public class UserService {

    private IUserMapper userMapper;
    private Mapper mapper;

    public UserService(IUserMapper userMapper, Mapper mapper) {
        this.userMapper = userMapper;
        this.mapper = mapper;
    }

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户信息
     */
    public FindByNameResponse findByName(String name) {
        UserEntity userEntity = userMapper.selectByName(name);
        if (null == userEntity) {
            log.info("查询到当前认证用户:[{}]", name);
            return null;
        }
        return mapper.map(userEntity, FindByNameResponse.class);
    }

}
