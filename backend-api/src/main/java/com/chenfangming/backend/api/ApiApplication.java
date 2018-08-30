package com.chenfangming.backend.api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 程序入口
 * @author fangming.chen
 * @since 2018-08-28 14:22:
 * Email cfmmail@sina.com
 */
@SpringBootApplication
@ComponentScan("com.chenfangming.backend")
@MapperScan("com.chenfangming.backend.core.persistence.mapper")
public class ApiApplication {
    /**
     * 主函数
     * @param args 运行参数
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiApplication.class);
        // 关闭启动Banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
