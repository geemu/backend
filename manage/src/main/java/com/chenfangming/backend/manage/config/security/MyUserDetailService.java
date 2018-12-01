package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.persistence.mapper.RoleMapper;
import com.chenfangming.backend.manage.persistence.mapper.UserMapper;
import com.chenfangming.common.model.response.DefaultResponseStatus.UserEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义用户认证
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 14:09
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 据用户名查找用户
     * @param userName 用户名
     * @return 用户
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //  查询用户
        UserEntity userEntity = userMapper.selectByName(userName);
        if (null == userEntity) {
            //  抛出用户名或密码错误，如果抛出用户名错误，会导致撞库
            throw new UsernameNotFoundException(UserEnum.USER_OR_PASSWORD_IN_CORRECT_ERROR.getMessage());
        }
        //  查询用户所拥有的角色  未删除且有效的
        List<RoleEntity> roleEntityList = roleMapper.selectByUserId(userEntity.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roleEntityList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getIsEnable(),
                true,
                true,
                true,
                authorities);
    }
}
