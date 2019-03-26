package com.chenfangming.backend.domain.response;

import lombok.Data;

/**
 * 用户登录成功的返回实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-09 21:06
 */
@Data
public class LoginSuccessResp {
    /** 用于请求的token **/
    private String accessToken;
    /** 用于刷新的token **/
    private String refreshToken;
}
