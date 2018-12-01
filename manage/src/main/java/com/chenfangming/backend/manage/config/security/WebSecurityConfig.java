package com.chenfangming.backend.manage.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * com.chenfangming.backend.manage.config.security
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 15:00
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.
                ignoring()
                .antMatchers("/login.html", "/static/**", "/login_p");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //  登录任何人都可以访问
                .mvcMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                //  设置认证后越权和匿名越权处理流程
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(myAuthenticationEntryPoint)
                    .accessDeniedHandler(myAccessDeniedHandler)
                .and()
//                //  前后端分离不需要session
//                    .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                //  前后端分离关闭csrf
                .csrf().disable()
                //  自定义认证方式
                .httpBasic()
                    .disable()
                .authorizeRequests()
               .and()
                .formLogin()
                .permitAll()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                //  注销行为任意访问
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .permitAll();

    }


}
