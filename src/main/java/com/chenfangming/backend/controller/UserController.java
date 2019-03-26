package com.chenfangming.backend.controller;

import com.chenfangming.backend.core.http.ResponseEntity;
import com.chenfangming.backend.persistence.entity.UserEntity;
import com.chenfangming.backend.service.UserService;
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
