package com.chenfangming.backend.controller;

import com.chenfangming.backend.core.exception.BusinessException;
import com.chenfangming.backend.core.http.DefaultResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统控制器.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-28 15:07
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(description = "系统控制器")
public class SystemController {

    @ApiOperation("获取页面的配置参数")
    @GetMapping("businessException")
    public String businessException() {
        throw new BusinessException(DefaultResponseStatus.AUTHENTICATION_EXCEPTION, "我的自定义异常");
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("exception")
    public String exception() {
        throw new RuntimeException("rasadad");
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("{page:[a-zA-Z]+}")
    public String properties(@PathVariable("page") String page) {
        return "这是" + page + "页面的配置参数";
    }

    @ApiOperation("获取页面的配置参数")
    @GetMapping("{page:[0-9]+}")
    public String properties2(@PathVariable("page") Integer page) {
        return "这是" + page + "页面的配置参数数字";
    }

}
