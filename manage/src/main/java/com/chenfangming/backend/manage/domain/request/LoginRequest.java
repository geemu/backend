package com.chenfangming.backend.manage.domain.request;

import lombok.Data;

/**
 * 登录请求实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-28 19:50
 */
@Data
public class LoginRequest {
    /** 用户名 **/
    private String name;
    /** 密码 **/
    private String password;
}
