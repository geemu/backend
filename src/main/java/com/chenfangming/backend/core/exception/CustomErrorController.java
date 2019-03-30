package com.chenfangming.backend.core.exception;

import com.chenfangming.backend.core.http.DefaultResponseStatus;
import com.chenfangming.backend.core.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-29 19:35
 */
@Slf4j
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {

    /** 404状态码 **/
    private static final int NOT_FOUND_404 = 404;
    /** 错误路径 **/
    @Value("${server.error.path:${error.path:/error}}")
    private String error;

    @RequestMapping
    public ResponseEntity<Object> handleError(HttpServletRequest request, HttpServletResponse response) {
        if (NOT_FOUND_404 == response.getStatus()) {
            return new ResponseEntity<>(DefaultResponseStatus.NOT_FOUND_EXCEPTION);
        }
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (null == throwable || !(throwable.getCause() instanceof BusinessException)) {
            log.error("后台未知异常:{}", throwable);
            return new ResponseEntity<>(DefaultResponseStatus.EXCEPTION);
        }
        return new ResponseEntity<>((BusinessException) throwable.getCause());
    }

    @Override
    public String getErrorPath() {
        return error;
    }
}
