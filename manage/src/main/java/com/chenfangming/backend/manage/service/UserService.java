package com.chenfangming.backend.manage.service;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.persistence.mapper.IUserMapper;
import com.chenfangming.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.chenfangming.common.model.response.DefaultResponseStatus.DATA_EXIST_EXCEPTION;
import static com.chenfangming.common.model.response.DefaultResponseStatus.EXCEPTION;

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
    @Autowired
    private Mapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 新增用户
     * @param request 请求参数
     * @return 新增用户的id
     */
    public Long post(UserEntity request) {
        UserEntity exist = userMapper.selectByName(request.getName());
        if (null != exist) {
            throw new BusinessException(DATA_EXIST_EXCEPTION);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Date now = new Date();
        request.setCreateTime(now);
        request.setUpdateTime(now);
        int result = userMapper.insert(request);
        if (result <= 0) {
            throw new BusinessException(EXCEPTION);
        }
        return request.getId();
    }

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户信息
     */
    public UserEntity selectByName(String name) {
        return userMapper.selectByName(name);
    }

}
