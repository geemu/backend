package com.chenfangming.backend.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
        bean.setHideUserNotFoundExceptions(false);
        bean.setUserDetailsService(myUserDetailService);
        bean.setPasswordEncoder(bCryptPasswordEncoder());
        return bean;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/static/**"
                        , "/login"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <T extends FilterSecurityInterceptor> T postProcess(T t) {
                        t.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        t.setAccessDecisionManager(myAccessDecisionManager);
                        return t;
                    }
                })
                .and().formLogin().loginProcessingUrl("login")
                .and().authorizeRequests().anyRequest().authenticated()
                .and()
                .cors().disable().csrf().disable().httpBasic().disable()
                .logout().clearAuthentication(true).logoutSuccessHandler(customAuthHandle)
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .authenticationEntryPoint(deniedHandler);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
