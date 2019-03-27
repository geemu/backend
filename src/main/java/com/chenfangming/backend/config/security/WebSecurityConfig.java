package com.chenfangming.backend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 15:00
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService myUserDetailService;
    private CustomDeniedHandle deniedHandler;
    private CustomMetadataSource myFilterInvocationSecurityMetadataSource;
    private CustomAccessDecisionManager myAccessDecisionManager;
    private CustomAuthHandle customAuthHandle;
    private ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
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
                .and()
                .addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //  设置匿名用户为0
                .anonymous().authorities("0")
                .and().cors().disable().csrf().disable().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().logout().clearAuthentication(true).logoutSuccessHandler(customAuthHandle)
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .authenticationEntryPoint(deniedHandler)
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
     * JSON登录
     * @return CustomAuthFilter
     * @throws Exception Exception
     */
    @Bean
    protected CustomAuthFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        CustomAuthFilter filter = new CustomAuthFilter(objectMapper);
        filter.setPostOnly(true);
        filter.setAuthenticationSuccessHandler(customAuthHandle);
        filter.setAuthenticationFailureHandler(customAuthHandle);
        //  重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
