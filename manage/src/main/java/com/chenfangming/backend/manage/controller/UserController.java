package com.chenfangming.backend.manage.controller;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import com.chenfangming.backend.manage.service.UserService;
import com.chenfangming.common.model.response.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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


    @ApiOperation(value = "新增用户", response = Long.class)
    @PostMapping
    public ResponseEntity<Long> post(UserEntity request) {
        Long id = userService.post(request);
        return new ResponseEntity<>(id);
    }
}
