package com.chenfangming.backend.manage.config.security.support;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.service.RoleService;
import com.chenfangming.backend.manage.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义加载用户数据.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 14:09
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    private UserService userService;
    private RoleService roleService;

    public MyUserDetailServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    /**
     * 据用户名查找用户.
     * @param userName 用户名
     * @return 用户
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public MyUserDetails loadUserByUsername(String userName) {
        //  查询用户
        UserEntity userEntity = userService.selectByName(userName);
        if (null == userEntity) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //  查询用户所拥有的角色  有效的
        Set<Long> roleIdSet = roleService.findByUserId(userEntity.getId());
        List<MySimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Long id : roleIdSet) {
            authorities.add(new MySimpleGrantedAuthority(id.toString()));
        }
        MyUserDetails response = new MyUserDetails();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getName());
        response.setPassword(userEntity.getPassword());
        response.setEnabled(userEntity.getEnabled());
        response.setAuthorities(authorities);
        return response;
    }
}
