package com.chenfangming.backend.core.service.impl;
import com.chenfangming.backend.core.enums.BaseResponseStatusEnum;
import com.chenfangming.backend.core.persistence.entity.User;
import com.chenfangming.backend.core.persistence.mapper.UserMapper;
import com.chenfangming.backend.core.service.IUserService;
import com.chenfangming.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现
 * @author fangming.chen
 * @since 2018-08-28 15:12:
 * Email cfmmail@sina.com
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名获取用户,未删除的用户
     * @param name 用户名
     * @return 用户
     */
    @Override
    public User getByName(String name) {
        User user = userMapper.selectByName(name);
        // 用户名不存在
        if (null == user) {
            throw new BusinessException(BaseResponseStatusEnum.USER_NOT_FOUND_EXCEPTION);
        }
        // 账号被锁定
        if (!user.getState()) {
            throw new BusinessException(BaseResponseStatusEnum.USER_LOCKED_EXCEPTION);
        }
        return user;
    }
}
