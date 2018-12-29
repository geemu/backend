package com.chenfangming.backend.manage.controller;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import com.chenfangming.backend.manage.service.MenuService;
import com.chenfangming.common.model.response.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 22:18
 */
@Slf4j
@Api(description = "菜单控制器")
@RequestMapping("menu")
@RestController
public class MenuController {
  private MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @ApiOperation(value = "获取用户菜单")
  @GetMapping
  public ResponseEntity<List<MenuEntity>> permission() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Set<Long> ids = new HashSet<>();
    authentication.getAuthorities().forEach((v) ->
            ids.add(Long.valueOf(v.getAuthority()))
    );
    return new ResponseEntity<>(menuService.selectUserMenu(ids));
  }
}
