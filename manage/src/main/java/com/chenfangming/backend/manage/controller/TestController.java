package com.chenfangming.backend.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.chenfangming.backend.manage.controller
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:55
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping
    public String test() {
        return "这是一个未配置的资源路径";
    }

    @GetMapping("url/1")
    public String url1() {
        return "固定url";
    }

    @GetMapping("url/{id}")
    public String urlPath1(@PathVariable int id) {
        return "参数url";
    }

    @PostMapping("user/add")
    public String userAdd() {
        return "用户新增";
    }

    @DeleteMapping("user/delete")
    public String userDelete() {
        return "用户删除";
    }

    @PutMapping("user/edit")
    public String userEdit() {
        return "用户修改";
    }

    @GetMapping("user/search")
    public String userSearch() {
        return "用户查询";
    }

    @PostMapping("directionary/add")
    public String directionaryAdd() {
        return "字典新增";
    }

    @DeleteMapping("directionary/delete")
    public String directionaryDelete() {
        return "字典删除";
    }

    @PutMapping("directionary/edit")
    public String directionaryEdit() {
        return "字典修改";
    }

    @GetMapping("directionary/search")
    public String directionarySearch() {
        return "字典查询";
    }


}
