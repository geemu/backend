package com.chenfangming.backend.core.exception;

import com.chenfangming.backend.core.http.DefaultResponseStatus;
import com.chenfangming.backend.core.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-29 19:35
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {

    /** 404状态码 **/
    private static final int NOT_FOUND_404 = 404;
    /** 错误路径 **/
    @Value("${server.error.path:${error.path:/error}}")
    private String error;

    @RequestMapping
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (NOT_FOUND_404 == statusCode) {
            return new ResponseEntity<>(DefaultResponseStatus.NOT_FOUND_EXCEPTION);
        }
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        throwable = throwable.getCause();
        if (throwable instanceof BusinessException) {
            BusinessException e = (BusinessException) throwable;
            return new ResponseEntity<>(e);
        }
        return new ResponseEntity<>(DefaultResponseStatus.EXCEPTION);
    }

    @Override
    public String getErrorPath() {
        return error;
    }
}
