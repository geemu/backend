package com.chenfangming.backend.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 管理后台程序入口
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:29
 */
@MapperScan("com.chenfangming.backend.manage.persistence.mapper")
@SpringBootApplication
public class ManageApplication {
    /**
     * 主函数
     * @param args 运行参数
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManageApplication.class);
        // 关闭启动Banner
        app.setBannerMode(org.springframework.boot.Banner.Mode.OFF);
        app.run(args);
    }
}
