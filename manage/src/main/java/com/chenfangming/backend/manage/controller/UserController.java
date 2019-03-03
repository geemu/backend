package com.chenfangming.backend.manage.controller;

import com.chenfangming.backend.manage.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 22:19
 */
@Slf4j
@RequestMapping("user")
@RestController
@Api(description = "用户控制器")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("FindByNameResponse")
    public Object test(String userName) {
        return userService.findByName(userName);
    }
}
