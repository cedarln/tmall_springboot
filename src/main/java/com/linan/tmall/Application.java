package com.linan.tmall;

import com.linan.tmall.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching //启动缓存
@EnableElasticsearchRepositories(basePackages = "com.linan.tmall.es") //放在dao下会跟其他链接redis的dao互相影响
@EnableJpaRepositories(basePackages = {"com.linan.tmall.dao", "com.linan.tmall.pojo"})
public class Application {
    static {
        PortUtil.checkPort(6379, "Redis 服务端", true);
        PortUtil.checkPort(9300, "ElasticSearch 服务端", true);
        PortUtil.checkPort(9300, "Kibana 工具", true);
    }
    public static void main(String[] args) {
        System.out.println("Hello tmall_springboot!");
        SpringApplication.run(Application.class, args);
    }
}
