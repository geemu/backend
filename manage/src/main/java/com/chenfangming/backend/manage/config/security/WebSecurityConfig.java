package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.config.security.filter.MyTokenFilter;
import com.chenfangming.backend.manage.config.security.filter.MyUsernamePasswordAuthenticationFilter;
import com.chenfangming.backend.manage.config.security.handle.*;
import com.chenfangming.backend.manage.config.security.support.MyAccessDecisionManager;
import com.chenfangming.backend.manage.config.security.support.MyFilterInvocationSecurityMetadataSource;
import com.chenfangming.backend.manage.config.security.support.MyUserDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 15:00
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /** 自定义加载用户数据. **/
    private MyUserDetailServiceImpl myUserDetailService;
    /** 处理认证用户权限不足. **/
    private MyAuthenticationDeniedHandler myAccessDeniedHandler;
    /** 未认证即匿名用户权限不足. **/
    private MyAnonymousDeniedHandle myAuthenticationEntryPoint;
    /** 处理注销成功. **/
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    /** 从数据库获取受保护对象. **/
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    /** 自定义鉴权逻辑. **/
    private MyAccessDecisionManager myAccessDecisionManager;
    /** 认证成功. **/
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /** 认证失败. **/
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    /** Token解析认证实体. **/
    private MyTokenFilter myTokenFilter;
    /** ObjectMapper. **/
    private ObjectMapper objectMapper;

    /**
     * 构造注入.
     * @param objectMapper objectMapper
     * @param myUserDetailService myUserDetailService
     * @param myAccessDeniedHandler myAccessDeniedHandler
     * @param myAuthenticationEntryPoint myAuthenticationEntryPoint
     * @param myLogoutSuccessHandler myLogoutSuccessHandler
     * @param myFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource
     * @param myAccessDecisionManager myAccessDecisionManager
     * @param myAuthenticationSuccessHandler myAuthenticationSuccessHandler
     * @param myAuthenticationFailureHandler myAuthenticationFailureHandler
     * @param myTokenFilter myTokenFilter
     */
    public WebSecurityConfig(ObjectMapper objectMapper,
                             MyUserDetailServiceImpl myUserDetailService,
                             MyAuthenticationDeniedHandler myAccessDeniedHandler,
                             MyAnonymousDeniedHandle myAuthenticationEntryPoint,
                             MyLogoutSuccessHandler myLogoutSuccessHandler,
                             MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource,
                             MyAccessDecisionManager myAccessDecisionManager,
                             MyAuthenticationSuccessHandler myAuthenticationSuccessHandler,
                             MyAuthenticationFailureHandler myAuthenticationFailureHandler,
                             MyTokenFilter myTokenFilter) {
        this.myUserDetailService = myUserDetailService;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.myFilterInvocationSecurityMetadataSource = myFilterInvocationSecurityMetadataSource;
        this.myAccessDecisionManager = myAccessDecisionManager;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.myTokenFilter = myTokenFilter;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestCache().disable().headers().cacheControl().disable()
                .and().addFilterAfter(myTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //  设置匿名用户为0
                .anonymous().authorities("0")
                .and().cors().disable().csrf().disable().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().logout().clearAuthentication(true).logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myAuthenticationEntryPoint)
                //  authenticated()的位置不能到最后，否则会控制不到权限
                .and().authorizeRequests().anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <T extends FilterSecurityInterceptor> T postProcess(T t) {
                        t.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        t.setAccessDecisionManager(myAccessDecisionManager);
                        return t;
                    }
                });
    }

    /**
     * JSON登录.
     * @return MyUsernamePasswordAuthenticationFilter
     * @throws Exception Exception
     */
    @Bean
    protected MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setPostOnly(true);
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        //  重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }


}
