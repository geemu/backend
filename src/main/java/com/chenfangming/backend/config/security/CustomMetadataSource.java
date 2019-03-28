package com.chenfangming.backend.config.security;

import com.chenfangming.backend.persistence.entity.RoleEntity;
import com.chenfangming.backend.persistence.entity.view.MenuRoleView;
import com.chenfangming.backend.persistence.mapper.IMenuMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 从数据库获取资源.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 17:44
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private IMenuMapper menuMapper;

    /**
     * 判断用户请求的资源是否在权限配置数据表中
     * 如果不在权限数据表中则直接放行，
     * 如果在权限数据表中，则返回当前url所能访问的角色集合，
     * 并返回给 decide 方法 {@link CustomAccessDecisionManager}
     * @param object 被保护的对象
     * @return 权限集合。如果没有，则返回一个空集合
     * @throws IllegalArgumentException 参数异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String path = method + ":" + requestUrl;
//        int a = 1/0;
        //  查询所有菜单及其可以访问的角色
        List<MenuRoleView> menuWithRoleList = menuMapper.selectAllWithRole();
        log.info("当前请求为->{}", path);
        for (MenuRoleView item : menuWithRoleList) {
            List<RoleEntity> roleEntityList = item.getRoleList();
            String pattern = item.getMethod() + ":" + item.getPath();
            boolean match = ANT_PATH_MATCHER.match(pattern, path) && !CollectionUtils.isEmpty(roleEntityList);
            log.info("[path->{}],[pattern->{}],[match->{}]", path, pattern, match);
            if (match) {
                int size = roleEntityList.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roleEntityList.get(i).getId().toString();
                }
                return SecurityConfig.createList(values);
            }
        }
        //  没有匹配上的资源返回null 后续就不需要再执行MyAccessDecisionManager了，而是直接访问
        //  如果是匿名用户，那么，将角色名从ROLE_ANONYMOUS改为0
        log.info("当前请求资源[{}]未配置，不需要鉴权(不需要执行MyAccessDecisionManager)，可直接访问", path);
        return null;
    }

    /**
     * 如果可用，则返回实现类定义的所有{@code ConfigAttribute}.
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 指示{@code Security Meta dataSource}实现.
     * 是否能够为指示的安全对象类型提供{@code ConfigAttribute}
     * @param clazz 正在查询的类
     * @return true 如果实现可以处理指示的类
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
