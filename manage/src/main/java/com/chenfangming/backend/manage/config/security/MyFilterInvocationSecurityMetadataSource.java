package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.persistence.entity.PermissionEntity;
import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import com.chenfangming.backend.manage.persistence.mapper.PermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 从数据库获取资源
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 17:44
 */
@Slf4j
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /** 匹配URL **/
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 判断用户请求的资源是否在权限配置数据表中，
     * 如果不在权限数据表中则直接放行，
     * 如果在权限数据表中，则返回当前url所能访问的角色集合，
     * 并返回给 decide 方法 {@link MyAccessDecisionManager}
     * @param object 被保护的对象
     * @return 权限集合。如果没有，则返回一个空集合
     * @throws IllegalArgumentException 参数异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.debug("当前请求方法为:{},路径为:{}", method, requestURI);
        String path = method + ":" + requestURI;
        //  权限及其可以访问的角色
        List<PermissionEntity> permissionEntityList = permissionMapper.select();
        for (PermissionEntity permission : permissionEntityList) {
            List<RoleEntity> roleEntityList = permission.getRoleEntityList();
            String pattern = permission.getMethod() + ":" + permission.getPath();
            //  pattern和请求url比较
            boolean hasPermission = antPathMatcher.match(pattern, path) && !CollectionUtils.isEmpty(roleEntityList);
            if (hasPermission) {
                int size = roleEntityList.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roleEntityList.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //  没有匹配上的资源返回null 后续就不需要再执行MyAccessDecisionManager了，而是直接访问
        return null;
    }

    /**
     * 如果可用，则返回实现类定义的所有{@code ConfigAttribute}。
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 指示{@code Security Meta dataSource}实现
     * 是否能够为指示的安全对象类型提供{@code ConfigAttribute}
     * @param clazz 正在查询的类
     * @return true 如果实现可以处理指示的类
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
