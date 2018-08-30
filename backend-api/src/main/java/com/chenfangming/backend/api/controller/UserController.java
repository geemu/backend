package com.chenfangming.backend.api.controller;
import com.chenfangming.backend.core.persistence.entity.User;
import com.chenfangming.backend.core.service.IUserService;
import com.chenfangming.common.model.BaseResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author fangming.chen
 * @since 2018-08-28 14:25:
 * Email cfmmail@sina.com
 */
@Api("用户控制器")
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据用户名获取用户，未删除且启用的用户
     * @param name 用户名
     * @return BaseResponse<User>
     */
    @PostMapping("login")
    public BaseResponse<User> getByName(String name) {
        return new BaseResponse<User>().success(userService.getByName(name));
    }
}
