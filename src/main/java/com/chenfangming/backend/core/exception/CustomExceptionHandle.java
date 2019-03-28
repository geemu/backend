package com.chenfangming.backend.core.exception;

import com.chenfangming.backend.core.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.chenfangming.backend.core.exception
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-28 20:16
 */
@Slf4j
@Component
@Configuration
@RestControllerAdvice
public class CustomExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Void> exceptionHandler(HttpServletRequest httpReq, HttpServletResponse httpResp, RuntimeException e) {
        log.error("{}", e);
        return new ResponseEntity<>();
    }
}
