package com.chenfangming.backend.manage.service;

import com.chenfangming.backend.manage.domain.request.LoginRequest;
import com.chenfangming.backend.manage.domain.response.VerificationCodeResponse;

/**
 * 公共操作。包括登录，登出等
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-01 18:01
 */
public interface PublicService {

    /**
     * 获取验证码
     * @return 验证码token以及base64格式的图片
     */
    VerificationCodeResponse getVerificationCode();

    /**
     * 登录
     * @param loginRequest 登录请求实体
     * @return 登录返回token
     */
    String login(LoginRequest loginRequest);
}
