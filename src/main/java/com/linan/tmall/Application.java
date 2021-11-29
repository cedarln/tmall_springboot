package com.linan.tmall;

import com.linan.tmall.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //启动缓存
public class Application {
    static {
        PortUtil.checkPort(6379, "Redis 服务端", true);
    }
    public static void main(String[] args) {
        System.out.println("Hello tmall_springboot!");
        SpringApplication.run(Application.class, args);
    }
}
