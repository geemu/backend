package com.chenfangming.backend.domain.request;

import lombok.Data;

/**
 * 用户登录的实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-09 21:01
 */
@Data
public class LoginReq {
    /** 用户名 **/
    private String name;
    /** 密码 **/
    private String password;
    /** 验证码标识 **/
    private String token;
    /** 验证码 **/
    private String code;
}
