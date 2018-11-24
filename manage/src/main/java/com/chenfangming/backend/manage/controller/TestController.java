package com.chenfangming.backend.manage.controller;

import com.chenfangming.common.model.response.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.chenfangming.backend.manage.controller
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:55
 */
@RestController
public class TestController {

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("这是需要权限的数据");
    }

    @GetMapping("logout")
    public ResponseEntity<Void> logout() {
        return new ResponseEntity<>();
    }
}
