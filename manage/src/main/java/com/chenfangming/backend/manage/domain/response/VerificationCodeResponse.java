package com.chenfangming.backend.manage.domain.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 验证码返回实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-30 16:50
 */
@Data
public class VerificationCodeResponse {
    @ApiModelProperty("验证码标识符")
    private String token;
    @ApiModelProperty("图片base64编码值")
    private String base64;
}
