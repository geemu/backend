package com.chenfangming.backend.controller;

import com.chenfangming.backend.config.security.SecurityUtils;
import com.chenfangming.backend.core.exception.BusinessException;
import com.chenfangming.backend.core.http.DefaultResponseStatus;
import com.chenfangming.backend.core.http.ResponseEntity;
import com.chenfangming.backend.domain.request.LoginCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.chenfangming.backend.core.http.DefaultResponseStatus.AUTHENTICATION_EXCEPTION;
import static com.chenfangming.backend.core.http.DefaultResponseStatus.EXCEPTION;

/**
 * 系统控制器.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-28 15:07
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(description = "系统控制器")
public class SystemController {

    @ApiOperation("获取页面的配置参数")
    @GetMapping("businessException")
    public String businessException() {
        throw new BusinessException(DefaultResponseStatus.AUTHENTICATION_EXCEPTION, "我的自定义异常");
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("exception")
    public String exception() {
        throw new RuntimeException("rasadad");
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("{page:[a-zA-Z]+}")
    public String properties(@PathVariable("page") String page) {
        return "这是" + page + "页面的配置参数";
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("{page:[0-9]+}")
    public String properties2(@PathVariable("page") Integer page) {
        return "这是" + page + "页面的配置参数数字";
    }

    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpReq;

    @ApiOperation("获取当前用户")
    @GetMapping("currentUser")
    public Object currentUser() {
        return SecurityUtils.getCurrentUser();
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginCondition condition) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(condition.getName(), condition.getPassword());
        ResponseEntity<String> response;
        try {
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = httpReq.getSession();
            // 这个非常重要，否则验证后将无法登陆
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            String sessionId = httpReq.getSession().getId();
            response = new ResponseEntity<>(sessionId);
        } catch (AuthenticationException e) {
            if (null == e.getCause()) {
                if (e instanceof DisabledException) {
                    response = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, "用户被禁用");
                } else if (e instanceof UsernameNotFoundException) {
                    response = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, "用户名不存在");
                } else if (e instanceof BadCredentialsException) {
                    response = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, "用户名或密码错误");
                } else {
                    response = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, e.getMessage());
                }
            } else {
                response = new ResponseEntity<>(EXCEPTION);
            }
        }
        return response;
    }
}


