package com.chenfangming.backend.manage.service.impl;

import com.chenfangming.backend.manage.domain.response.FindByNameResponse;
import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.persistence.mapper.IUserMapper;
import com.chenfangming.backend.manage.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * com.chenfangming.backend.manage.service.impl
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 13:23
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private IUserMapper userMapper;
    private Mapper mapper;

    /**
     * 构造器注入.
     * @param userMapper userMapper
     * @param mapper mapper
     */
    public UserServiceImpl(IUserMapper userMapper, Mapper mapper) {
        this.userMapper = userMapper;
        this.mapper = mapper;
    }

    /**
     * 根据用户名查询用户.
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public FindByNameResponse findByName(String userName) {
        UserEntity userEntity = userMapper.selectByName(userName);
        if (null == userEntity) {
            log.info("数据库未查询到当前认证用户:[{}],返回null", userName);
            return null;
        }
        return mapper.map(userEntity, FindByNameResponse.class);
    }
}
