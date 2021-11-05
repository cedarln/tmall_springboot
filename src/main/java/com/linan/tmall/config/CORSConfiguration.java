package com.linan.tmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter{//配置类，用于允许所有的请求都跨域。
//    因为是二次请求，第一次获取 html 页面， 第二次通过 html 页面上的 js 代码异步获取数据，
//    一旦部署到服务器就容易面临跨域请求问题，所以允许所有访问都跨域，就不会出现通过 ajax 获取数据获取不到的问题了。
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //从哪儿来的请求
                .allowedMethods("*") //调用什么方法
                .allowedHeaders("*"); //携带什么头信息
    }
}
