package com.chenfangming.backend.config.security;

import com.chenfangming.backend.core.util.StringUtils;
import com.chenfangming.backend.persistence.entity.UserEntity;
import com.chenfangming.backend.service.RoleService;
import com.chenfangming.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义加载用户数据
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 14:09
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private UserService userService;
    private RoleService roleService;


    /**
     * 据用户名查找用户
     * @param userName 用户名
     * @return 用户
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public CustomUserDetails loadUserByUsername(String userName) {
        log.info("用户【{}】执行登录", userName);
        if (StringUtils.isEmpty(userName)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        //  查询用户
        UserEntity userEntity = userService.selectByName(userName);
        if (null == userEntity) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //  查询用户所拥有的角色  有效的
        Set<Long> roleIdSet = roleService.findByUserId(userEntity.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Long id : roleIdSet) {
            authorities.add(new SimpleGrantedAuthority(id.toString()));
        }
        CustomUserDetails response = new CustomUserDetails();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getName());
        response.setPassword(userEntity.getPassword());
        response.setEnabled(userEntity.getEnabled());
        response.setAuthorities(authorities);
        return response;
    }

}
