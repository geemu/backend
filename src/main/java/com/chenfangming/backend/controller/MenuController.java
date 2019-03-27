package com.chenfangming.backend.controller;

import com.chenfangming.backend.core.http.ResponseEntity;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 22:18
 */
@Slf4j
@RequestMapping("menu")
@RestController
@AllArgsConstructor
@Api(description = "菜单控制器")
public class MenuController {

    @PostMapping
    public ResponseEntity<Long> post() {
        log.info("用户新增");
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        log.info("用户删除");
        return null;
    }

    @PutMapping
    public ResponseEntity<Long> put() {
        log.info("用户修改");
        return null;
    }

    @GetMapping
    public ResponseEntity<Void> get() {
        log.info("用户查询");
        return null;
    }
}
