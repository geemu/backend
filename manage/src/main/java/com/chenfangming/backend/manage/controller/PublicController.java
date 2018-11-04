package com.chenfangming.backend.manage.controller;

import com.chenfangming.backend.manage.domain.request.LoginRequest;
import com.chenfangming.backend.manage.domain.response.VerificationCodeResponse;
import com.chenfangming.backend.manage.service.PublicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共控制器。包括登录，登出等
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-28 18:19
 */
@Validated
@Api(tags = "公共控制器")
@RestController
public class PublicController {

    /** 公共操作 **/
    private PublicService publicService;

    @Autowired
    private void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }

    @ApiOperation(value = "获取验证码", response = VerificationCodeResponse.class)
    @GetMapping("captcha")
    public VerificationCodeResponse getVerificationCode() {
        return publicService.getVerificationCode();
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return publicService.login(loginRequest);
    }

    @ApiOperation("登出")
    @PostMapping("logout")
    public void logout(@RequestHeader String token) {

    }

}
