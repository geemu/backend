package com.chenfangming.backend.manage.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-28 19:50
 */
@Data
public class LoginRequest {
    /** 用户名 **/
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度1-20")
    @ApiModelProperty("用户名")
    private String name;
    /** 密码 **/
    @NotBlank(message = "密码不能为空")
    @Length(min = 1, max = 20, message = "密码长度1-20")
    @ApiModelProperty("密码")
    private String password;
    /** 验证码标识符 **/
    @NotBlank(message = "非法请求")
    @ApiModelProperty("验证码标识符")
    private String token;
    /** 验证码值 **/
    @NotBlank(message = "验证码不能为空")
    @Length(min = 4, max = 4, message = "密码长度1-20")
    @ApiModelProperty("验证码值")
    private String value;
}
